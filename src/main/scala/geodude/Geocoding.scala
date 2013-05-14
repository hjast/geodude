package geodude

import net.liftweb.common.Box
import net.liftweb.util.LRU

/** A case class representing a location */
case class Location(name: String, lng: Double, lat: Double)

trait CachedBackedGeocoding extends Geocoding {

  def geo: Geocoding

  def cache(loc: Location): Unit

  def get(address: String): Box[Location]

  def location(address: String): Box[Location] = {
    get(address).orElse {
      val optLoc = geo.location(address)
      optLoc.foreach(cache)
      optLoc
    }
  }
}

case class InMemoryCachedGeocoding(geo: Geocoding) extends CachedBackedGeocoding {

  val cache = new LRU[String, Location](5000)

  def cache(loc: Location): Unit = cache.update(loc.name, loc)

  def get(address: String): Box[Location] = cache.get(address)
}


/** A reverse geocoder **/
trait Geocoding {
  def location(name: String): Box[Location]
}

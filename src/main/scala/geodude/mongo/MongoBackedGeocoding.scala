package geodude.mongo

import geodude.{CachedBackedGeocoding, Location, Geocoding}
import com.foursquare.rogue.LiftRogue._

case class MongoBackedGeocoding(geo: Geocoding) extends CachedBackedGeocoding {

  def get(address: String) =
    GeocodeDoc.where(_.location eqs address).get.map(a => Location(address, a.lng.is, a.lat.is))

  def cache(l: Location) =
    GeocodeDoc.createRecord.location(l.name).lat(l.lat).lng(l.lng).save

}

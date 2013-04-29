package geodude.googlemaps

import net.liftweb.json.{Extraction, JsonParser, DefaultFormats}
import dispatch.classic.{Http, url}
import net.liftweb.util.Helpers._
import geodude.{Location, Geocoding}
import net.liftweb.common.Box

/** A google maps implementation of the geo coder **/
object GoogleMapsGeocoding extends Geocoding {

  val endPoint = "http://maps.googleapis.com/maps/api/geocode/json"

  val defaultParams = List(
    ("sensor", "true"),
    ("region", "en"),
    ("language", "en"))

  def location(address: String): Box[Location] = {
    implicit val formats = DefaultFormats
    val req = url(endPoint) <<? (("address", address) :: defaultParams)
    Http(req >- { a =>
       tryo(JsonParser.parse(a)).flatMap { json =>
          Extraction.extractOpt[Response](json)
        }
      }
    ).openOrThrowException("This has something").results.map(_.geometry.location).map(a => Location(address, a.lng, a.lat)).headOption
  }
}

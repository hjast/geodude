package geodude.openstreet

import java.net.URL
import java.net.URLEncoder
import geodude.{Geocoding, Location}
import net.liftweb.util.Helpers
import net.liftweb.common.Box
import xml.XML
import geodude.{Geocoding, Location}

/** An Open street map implement of the geocoder */
object OpenStreetmapGeocoding extends Geocoding {
  def location(name: String): Box[Location] = {
    Helpers.tryo {
      val xml = XML.load(new URL("http://nominatim.openstreetmap.org/search?q=" + URLEncoder.encode(name, "utf-8") + "&format=xml"))
      val lng = ((xml \\ "place" head) \\ "@lon") text
      val lat = ((xml \\ "place" head) \\ "@lat") text
      def parseDouble(s: String) = java.lang.Double.parseDouble(s)
      new Location(name, parseDouble(lng), parseDouble(lat))
    }
  }
}

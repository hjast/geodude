package geodude.googlemaps


case class Response(status: String, results: List[Result])

case class Result(types: List[String],
                  formatted_address: String,
                  address_components: List[AddressComponent],
                  geometry: Geometry,
                  partial_match: Option[Boolean])

case class AddressComponent(long_name: String,
                            short_name: String,
                            types: List[String])

case class Geometry(location: GeoPoint,
                    location_type: String,
                    viewport: ViewPort,
                    bounds: Option[ViewPort])

case class ViewPort(southwest: GeoPoint,
                    northeast: GeoPoint)

case class GeoPoint(lat: Double, lng: Double)





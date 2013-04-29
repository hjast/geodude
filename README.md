# geodude -- **scala-geocoder** #

Geocode, a scala wrapper for reverse geocoding. This was born because I needed to bring in a lot of
data and tag it with lat / lng. In testing / etc.. I keep hitting limits and/or API's were down so
I created a cached geocoder with different backend implementations

## goals

- To let you use geocoders without writing wrappers and allowing you to switch them out. Also
giving you a cache so you never don't need to geocode twice.

## usage

Simple usage

```scala
import geodude._
val str = googleMaps.location("690 Prospect PL, Brooklyn, NY")
                    .map(loc => "%s is at %s lat and %s lng" format(loc.name, loc.lat, loc.lng))
                    .getOrElse("Geocoding could not find address")

println(str)
```
Slightly more complicated using a cache.

```scala
import geodude._

val geo = InMemoryCachedGeocoding(googleMaps)

/** This only hits the geocoding once **/
val latLngs = (for {
  r <-  0 until 10
  loc <- geo.get("690 Prospect Pl, Brooklyn, NY")
} yield {
   println("%s is at %s lat and %s lng" format(loc.name, loc.lat, loc.lng))
   loc
})

```

This example shows cascading between geocoders if one does not find

```scala

googleMaps.location("Some crazier address").openOr(openStreetMaps.location("690 Prospect Pl"))
/** Returns full box with 690 Prospect Pl **/

```

## todos

Switch to dispatch reboot + make these futures.
Add in support for a generic caching layer (??).
Add in support for other map operations.
Document the mongo backing.
Write tests


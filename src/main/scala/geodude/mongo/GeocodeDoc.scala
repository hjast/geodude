package geodude.mongo

import net.liftweb.mongodb.record.{ MongoMetaRecord, MongoId, MongoRecord }
import net.liftweb.record.field.{ DoubleField, StringField }

/** Just used as a cache for geocode stuff **/
class GeocodeDoc extends MongoRecord[GeocodeDoc]
  with MongoId[GeocodeDoc]{

  def meta = GeocodeDoc

  object location extends StringField(this, 512)

  object lat extends DoubleField(this)

  object lng extends DoubleField(this)

}

object GeocodeDoc extends GeocodeDoc with MongoMetaRecord[GeocodeDoc]


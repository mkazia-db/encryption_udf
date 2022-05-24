package com.fhlbc.encryption.fernet

import com.macasaet.fernet.{Key, StringValidator, Token}
import org.apache.hadoop.hive.ql.exec.UDF
import java.time.{Duration,Instant}
import java.time.temporal.TemporalAmount

class Validator extends StringValidator {

  override def getTimeToLive() : TemporalAmount = {
    Duration.ofSeconds(Instant.MAX.getEpochSecond());
  }
}

class udfFernetDecrypt extends UDF {

  def evaluate(inputVal: String, key: String): String = {

    if( inputVal != null && inputVal!="" ) {
      val keys: Key = new Key(key)
      val token = Token.fromString(inputVal)
      val validator = new Validator() {}
      val payload = token.validateAndDecrypt(keys, validator)
      payload
    } else return inputVal
  }
}

class udfFernetEncrypt extends UDF {

  def evaluate(inputVal: String, key: String): String = {

    if( inputVal != null && inputVal!="" ) {
      val keys: Key = new Key(key)
      val token = Token.generate(keys, inputVal)
      val payload = token.serialise
      payload
    } else return inputVal
  }
}

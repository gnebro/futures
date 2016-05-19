package org.test

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Span, Seconds}
import org.scalatest.time.Millis
import org.scalatest.{Matchers, FlatSpec}

class FutureTestWithScalazWriterSpec extends FlatSpec with ScalaFutures with Matchers {

  import FutureTestWithScalazWriter._

  implicit override val patienceConfig =
    PatienceConfig(timeout = scaled(Span(2, Seconds)), interval = scaled(Span(5, Millis)))

  "concatenateNTimes " should " aggiungere la stessa stringa n volte" in {
    val result = concatenateNTimes(4)("Babbo")
    result shouldBe "BabboBabboBabboBabboBabbo"
  }

  "getFirstFibonacci " should " ritornare in fibonacci nella posizione 90" in {
    val futureResult = getFirstFibonacci(90)
    whenReady(futureResult.value) { result =>
      result shouldBe "2880067194370816120"
    }
  }

  "getFirstFibonacci " should " ritornare in fibonacci nella posizione 88" in {
    val futureResult = getFirstFibonacci(88)
    whenReady(futureResult.value) { result =>
      result shouldBe "1100087778366101931"
    }
  }

  "getFirstNFibonacci " should " ritornare la lista dei primi 10 fibonacci" in {
    val futureResult = getFirstNFibonacci(11)
    whenReady(futureResult.value) { result =>
      result shouldBe "0,1,1,2,3,5,8,13,21,34,55,"
    }
  }

  "test " should " ritornare qualcosa" in {
    val futureResult = test(11)
    whenReady(futureResult) { result =>
      result shouldBe "89@0,1,1,2,3,5,8,13,21,34,55,89@0,1,1,2,3,5,8,13,21,34,55,89@0,1,1,2,3,5,8,13,21,34,55,89@0,1,1,2,3,5,8,13,21,34,55,89@0,1,1,2,3,5,8,13,21,34,55,89@0,1,1,2,3,5,8,13,21,34,55,89@0,1,1,2,3,5,8,13,21,34,55,89@0,1,1,2,3,5,8,13,21,34,55,89@0,1,1,2,3,5,8,13,21,34,55,89@0,1,1,2,3,5,8,13,21,34,55,89@0,1,1,2,3,5,8,13,21,34,55,89@0,1,1,2,3,5,8,13,21,34,55,"
    }
  }

}



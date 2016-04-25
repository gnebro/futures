package org.test

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, FlatSpec}
import scala.concurrent.Future

class FutureTestSpec extends FlatSpec with ScalaFutures with Matchers {

  import FutureTest._
  import FutureTestSpec._

  "concatenateNTimes " should " aggiungere la stessa stringa n volte" in {
    val result = concatenateNTimes(4)("Babbo")
    result shouldBe "BabboBabboBabboBabboBabbo"
  }

  "concatenateNTimesFutures " should " aggiungere la stessa stringa n volte" in {
    val futureResult = concatenateNTimesFutures(4)("Babbo")
    whenReady(futureResult) { result =>
      result shouldBe "BabboBabboBabboBabboBabbo"
    }
  }

  "getFirstFibonacci " should " ritornare in fibonacci nella posizione 90" in {
    val futureResult = getFirstFibonacci(90)
    whenReady(futureResult) { result =>
      result shouldBe "2880067194370816120"
    }
  }

  "getFirstFibonacci " should " ritornare in fibonacci nella posizione 88" in {
    val futureResult = getFirstFibonacci(88)
    whenReady(futureResult) { result =>
      result shouldBe "1100087778366101931"
    }
  }

  "getFirstNFibonacci " should " ritornare la lista dei primi 10 fibonacci" in {
    val futureResult = getFirstNFibonacci(11)
    whenReady(futureResult) { result =>
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

object FutureTestSpec {

  private val FutureSuccess: Future[String] = Future.successful("future successful")
  private val FutureFailed: Future[String] = Future.failed(new Exception("future failed"))
  private val ListOfString = "aa" :: "" :: "babbo" :: "none" :: "b" :: Nil
  private val ListOfOptionString = Some("aa") :: None :: Some("babbo") :: Some("none") :: None :: Nil
  private val ListOptionInt = None :: Some(1) :: Some(87) :: None :: Some(-3) :: Nil

}

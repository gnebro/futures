package org.test

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, FlatSpec}
import scala.concurrent.{ExecutionContext, Future}
import ExecutionContext.Implicits.global
import scala.util.Failure

class FutureFallbackSpec extends FlatSpec with ScalaFutures with Matchers {

  import FutureFallback._
  import TestFeature._

  "fallback " should " fallback to a backup future" in {
    whenReady(fallback) { result =>
      result shouldBe "2880067194370816120"
    }
  }

  "noFallback " should " NOT fallback to a backup future" in {
    whenReady(noFallback) { result =>
      result shouldBe "future successful"
    }
  }

  "noRecover " should " NOT recover and return the original future value" in {
    whenReady(noRecover) { result =>
      result shouldBe "future successful"
    }
  }

  "recover " should " recover and wrap the new string in a Future" in {
    whenReady(recover) { result =>
      result shouldBe "something bad happened"
    }
  }

  "recoverWithNoCause " should " throw an exception as the exception has no cause" in {
    recoverWithNoCause onComplete {
      // returns a Failure(null) as the original failure has no cause
      case a => a.failed.get shouldBe null
    }
  }

  "recoverNoMatch " should " NOT recover because there is no match and return the original failed Future" in {
    recoverNoMatch onComplete {
      case a =>
        // returns Failure(java.lang.Exception: future failed)
        a.failed.get shouldBe FutureFailed
    }

  }

}

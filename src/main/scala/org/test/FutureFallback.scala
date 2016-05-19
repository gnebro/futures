package org.test

import scala.concurrent.{ExecutionContext, Future}
import ExecutionContext.Implicits.global
import Utils._

object FutureFallback {

  import TestFeature._

  def futureConsuming: Future[String] = getFirstFibonacci(90)

  def fallback: Future[String] = {
    FutureFailed fallbackTo(futureConsuming)
  }

  def noFallback: Future[String] = {
    FutureSuccess fallbackTo(futureConsuming)
  }

  def noRecover: Future[String] = {
    FutureSuccess recover {
      case _ => "Someting whent wrong"
    }
  }

  def recover: Future[String] = {
    Future.failed(FailureExceptionCause) recover {
      case e: Exception => "something bad happened"
    }
  }

  def recoverWithNoCause: Future[String] = {
    FutureFailed recover {
      case e: Exception => "something bad happened"
    }
  }

  def recoverChained: Future[String] = {
    FutureSuccess recover {
      case e: NumberFormatException => "Someting whent wrong"
    } recover {
      case e: ArrayIndexOutOfBoundsException => "something bad happened"
    }
  }

  def recoverNoMatch: Future[String] = {
    Future.failed(FailureExceptionCause) recover {
      case e: AbstractMethodError => "should not have recovered"
    }
  }





  private def getFirstFibonacci (index: Int): Future[String] = {
    Future{
      Thread.sleep(20)
      fib(index).toString
    }
  }


}

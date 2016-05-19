package org.test

import java.util.Date

import scala.annotation.tailrec
import scala.concurrent.{ExecutionContext, Future}
import ExecutionContext.Implicits.global
import scalaz._
import Scalaz._
import Utils._


object FutureTestWithScalazWriter {

  // the left side can be any monoid. E.g something which support
  // concatenation and has an empty function: e.g. String, List, Set etc.
  type Result[T] = Writer[List[String], T]

  def concatenateNTimes (times: Int)(input: String): String = {
    @tailrec
    def concatenate(times: Int, acc: String): String = {
      if(acc == null) acc
      else if(times < 1 || times == 0) acc
      else concatenate(times - 1, acc + input)
    }
    concatenate(times, input)
  }

  def getFirstFibonacci (index: Int): Result[Future[String]] = {
    val init = System.currentTimeMillis()
    val result = Future{
      Thread.sleep(2000)
      fib(index).toString
    }
    result.set(List("getFirstFibonacci started at "+new Date(init)))
  }

  def getFirstNFibonacci (n: Int): Result[Future[String]] = {
    val init = System.currentTimeMillis()
    val result = Future{
      Thread.sleep(1000)
      fib.take(n).foldLeft("")((acc, number) => {acc + number + ","})
    }
    result.set(List("getFirstNFibonacci started at "+new Date(init)))
  }

  def test(i: Int): Future[String] = {
    val init = System.currentTimeMillis()
    val fib = getFirstFibonacci(i)
    val firstFib = getFirstNFibonacci(i)
    val concatenate = concatenateNTimes(i)_

    val combined = for {
      numFib <- fib
      numsFib <- firstFib
      res <- {val fut = numFib.zip(numsFib).map(t => concatenate(t._1+"@"+t._2))
        fut.set(List("test started at "+new Date(init)))
      }
    } yield res

    println(combined.run)
    combined.value
  }

}

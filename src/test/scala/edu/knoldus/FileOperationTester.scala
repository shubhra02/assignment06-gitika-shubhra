package edu.knoldus

import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class FileOperationTester extends FunSuite {

  test("Check Valid Directory") {
    assert(FileOperation.checkValidDirectory(".\\src\\test\\resources"))
  }

  test("Check Size") {
    val fList = FileOperation.getListOfFilesAndFolders(".\\src\\test\\resources")
    val finalList = Await.result(fList, 5.second)
    assert(finalList.size == 7)
  }

  test("Check Size of Empty folder") {
    val fList = FileOperation.getListOfFilesAndFolders(".\\src\\main\\resources")
    val finalList = Await.result(fList, 5.second)
    assert(finalList.size == 0)
  }

}

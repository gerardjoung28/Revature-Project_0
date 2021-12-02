
import Data_Base_Connect.{connect, connect_close, connection}

import Console._

object New_User {
  var user_id = 0
  var name = ""
  var last_name = ""
  var phone = ""
  var email = ""
  var address = ""
  var query = ""

  def New_User(user_id: Int ,name: String,last_name:String, phone:String, email:String, address:String) : Unit ={
    this.user_id = user_id
    this.name = name
    this.last_name = last_name
    this.phone = phone
    this.email = email
    this.address = address
  }

  def Save_to_Database(query: String): Unit ={

    val statement = Data_Base_Connect.connection.createStatement()
    val resultSet = statement.executeUpdate(query)
  }

  // return a single value from database
  def Read_From_Database(query:String, column:Int): String ={

    val statement = Data_Base_Connect.connection.createStatement()
    var resultSet = statement.executeQuery(query)
    resultSet.next()
    var r = resultSet.getString(column)
    r

  }

  def Read_All_fromdatabase(query:String): Unit = {

    val statement = Data_Base_Connect.connection.createStatement()
    val resultSet = statement.executeQuery(query)

    while ( resultSet.next() ) {
      println("name: " +resultSet.getString(2))
      println("last name: " +resultSet.getString(3))
      println("phone: " +resultSet.getString(4))
      println("email: " + resultSet.getString(5))
      println("address: " + resultSet.getString(6))


    }

  }
}


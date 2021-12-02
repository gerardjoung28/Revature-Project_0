import java.sql.DriverManager
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

object Data_Base_Connect {
  val driver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://localhost:3306/car"
  val username = "root"
  val password = "thenewlife24"
  var connection:Connection = null
  var query = ""

  def connect() : Unit =  {
    // connect to the database named "Car" on the localhost

    connection = DriverManager.getConnection(url, username, password)

  }

   // close connection
  def connect_close(): Unit ={
    connection.close()
  }

}

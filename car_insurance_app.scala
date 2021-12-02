
import scala.io.StdIn.readInt
import scala.io.StdIn.readLine
import Data_Base_Connect.{connect, connect_close}

import scala.sys.process._
import Console._
import New_User.{Read_All_fromdatabase, Read_From_Database, Save_to_Database}

import java.sql.SQLException

object car_insurance_app{

    var r = scala.util.Random
    var user_id = 0
    var name = ""
    var last_name = ""
    var phone = ""
    var email = ""
    var address = ""
    var query = ""
    var username = ""
    var password = ""
    var car_make = ""
    var car_model = ""
    var car_year = ""


    def main_menu(): Unit ={

        println ("***** Welcome to Car_Insurance *****")
        println("Select an Option below: ")
        println("1. Create an Account")
        println("2. Log In ")
    }

    def create_account(): Unit = {

        name = readLine("Enter your name:")
        last_name = readLine("Enter your last name:")
        phone = readLine("Enter your phone:")
        email = readLine("Enter your email:")
        address = readLine("Enter your address: ")
        user_id = r.nextInt(100)

        New_User.New_User(user_id, name , last_name,phone,email, address)
        query = "Insert into customer (user_id, name,  last_name, phone_number, email, address) values("+user_id+", '"+name+"', '"+last_name+"', '"+phone+"', '"+email+"', '"+address+"')"
        connect()
        Save_to_Database(query)



        println("Please create username and password: ")
        username = readLine("username: ")
        password = readLine("password: ")
        println("*** Your account have been successfully created *** ")

        query = "Insert into account(user_id, passwordkey, username) values("+user_id+", '"+password+"', '"+username+"')"
        Save_to_Database(query)
        connect_close()

    }

    def log_in():Unit = {

        var customer_id:Any = 0
        var temp = ""
        username = readLine("username: ")
        connect()

       try {
            query = "Select username from account where username = '"+username+"' ;"
            temp = Read_From_Database(query,1)

            query = "Select user_id from account where  username = '"+username+"'"
            customer_id = Read_From_Database(query,1)

            if(username == temp) {
                password = readLine("password: ")
                query = "Select passwordkey from account where passwordkey = '"+password+"' ;"
                temp = Read_From_Database(query,1)
                if(temp == password) println("")
            }


        }
        catch {
            case e: SQLException => println("wrong username or password ")
            System.exit(0)
        }

        def sub_menu():Unit =   {
         println("Welcome to your account")
        println("1. Account Info")
        println("2. Add your car")
        println("3. exit ")}

        sub_menu()
        val select = readInt()

        if(select==1){
            query = "Select * From Customer Where user_id = '"+customer_id+"'"
            Read_All_fromdatabase(query)
        }
        else if(select==2) {
          car_make = readLine("Car make: ")
          car_model = readLine("Car model: ")
          car_year = readLine("Car year: ")

          query = "Insert into car_data(user_id, make, model, car_year) values("+user_id+", '"+car_make+"', '"+car_model+"', '"+car_year+"');"
          Save_to_Database(query)

          println("Car Successfully saved ")
          sub_menu()
        }
        else System.exit(0)

        connect_close()
    }

    def main(args: Array[String]): Unit = {

      //
      main_menu()
      val select = readInt()

        if (select == 1) { create_account()
        println("Please select an option below")
            println("1. Log in ")
            println("2. exit")
            val select2 = readInt()
            if(select2 == 1)  log_in()
            else System.exit(0)
        }

            else if(select == 2) log_in()

        else { println("Please enter a valid input")
             main_menu()

        }


    }
}
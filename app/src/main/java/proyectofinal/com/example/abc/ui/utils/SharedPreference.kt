package proyectofinal.com.example.abc.ui.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import proyectofinal.com.example.abc.model.LoginResponseDTO

class SharePreference(context : Context)
{
    val context = context
    val prefs: SharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE)

    fun getString(key: String ) : String?{

        return prefs.getString(key, "")
    }
    fun setString(key: String, value: String) {
        val editor = prefs.edit()
        editor.putString(key,value)
        editor.apply()
    }
    fun setUserLogged(value: LoginResponseDTO){
        val gson = Gson()
        val objetoComoJson = gson.toJson(value)
        val editor = prefs.edit()
        editor.putString("user", objetoComoJson)
        editor.apply()
    }

    fun getUserLogged() : LoginResponseDTO?{
        val userLogged: String? = prefs.getString("user",null)
        if( userLogged != null && (userLogged as String).isNotEmpty())
            return Gson().fromJson(userLogged,LoginResponseDTO::class.java)
        return null
    }

}
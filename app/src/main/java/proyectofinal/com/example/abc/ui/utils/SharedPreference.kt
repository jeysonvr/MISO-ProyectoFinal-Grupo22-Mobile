package proyectofinal.com.example.abc.ui.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import proyectofinal.com.example.abc.model.LoginResponseDTO

class SharePreference(context : Context)
{
    val prefs: SharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefs.edit()

    fun getString(key: String ) : String?{

        return prefs.getString(key, "")
    }
    fun setString(key: String, value: String) {
        editor.putString(key,value)
        editor.apply()
    }
    fun setUserLogged(value: LoginResponseDTO){
        val gson = Gson()
        val objetoComoJson = gson.toJson(value)
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
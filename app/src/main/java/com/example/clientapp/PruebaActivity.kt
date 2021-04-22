package com.example.clientapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class PruebaActivity : AppCompatActivity() {
    private lateinit var rv_chofer: RecyclerView

    val arrayList = ArrayList<Chofer>()
    val displayList = ArrayList<Chofer>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba)
        rv_chofer =  findViewById<RecyclerView>(R.id.recycler_choferes)


        val url= "http://192.168.0.103/Projects/Motoservice/Controller/listar_choferes.php"
        val queue = Volley.newRequestQueue(this)

        /*Primer parametro es el metodo, luego la url y despues la respuesta*/
        val stringRequest = StringRequest(Request.Method.GET,url, Response.Listener { response ->
            val jsonArray = JSONArray(response)

            for(i in 0 until jsonArray.length() ){
                val jsonObject = JSONObject(jsonArray.getString(i))
                val nombre =  jsonObject.get("nombre_chofer_b").toString()
                val email = jsonObject.get("email_chofer_b").toString()
                val celular = jsonObject.get("telefono_chofer_b").toString()
                val foto = jsonObject.get("foto_chofer_b").toString()
                arrayList.add(Chofer(nombre,email,celular,foto))
                //Toast.makeText(applicationContext,text.toString(),Toast.LENGTH_LONG).show()
            }
                displayList.addAll(arrayList)
                val myAdapter = ChoferAdapter(displayList)
                rv_chofer.layoutManager = LinearLayoutManager(this)
                rv_chofer.adapter = myAdapter
         },Response.ErrorListener {
            "Algo salió mal :(."
        })
        queue.add(stringRequest)


    }




}
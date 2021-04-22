package com.example.clientapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


/*El adapter se encarga de recolectar información y configurar todoo para que el RV
pueda renderizar todos los items pero quien realmente renderiza es el viewHolder*/
class ChoferAdapter (val choferes:List<Chofer>):RecyclerView.Adapter<ChoferAdapter.ChoferHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoferHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChoferHolder(layoutInflater.inflate(R.layout.item_chofer,parent, false))
    }

    /*Instancia de nuestro viewHolder, y la posición a rederizar*/
    override fun onBindViewHolder(holder: ChoferHolder, position: Int) {
        /* El holder puede hacer lo que tenemos en la clase de abajo*/
        holder.render(choferes[position])
    }

    override fun getItemCount(): Int = choferes.size

    class  ChoferHolder(val view: View):RecyclerView.ViewHolder(view) {

        fun render(choferes: Chofer){
            view.findViewById<TextView>(R.id.tv_nombre_chofer).text = choferes.nombre_chofer
            view.findViewById<TextView>(R.id.tv_email_chofer).text = choferes.email_chofer
            view.findViewById<TextView>(R.id.tv_celular_chofer).text = "Tel: "+choferes.celular_chofer

            Picasso.get().load(choferes.foto_chofer).into(view.findViewById<ImageView>(R.id.iv_foto_chofer))

        }
    }




}

package app.com.roomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShopAdapter : RecyclerView.Adapter<ShopAdapter.ShopHolder>() {
       private var dataList = emptyList<Shopping>() // you can't use normal list as it won't be initialized until data arrive from database to this list yup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.rowview,parent,false)
        return ShopHolder(view)
    }

    override fun onBindViewHolder(holder: ShopHolder, position: Int) {
    val item = dataList[position]
        holder.text2.text = item.name
        holder.text1.text = item.amount.toString()

    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    fun setSHopping(it: List<Shopping>) {
        this.dataList = it
        notifyDataSetChanged()
    }


    class ShopHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text1 = itemView.findViewById<TextView>(R.id.textView1)
        val text2 = itemView.findViewById<TextView>(R.id.textView2)
    }
}
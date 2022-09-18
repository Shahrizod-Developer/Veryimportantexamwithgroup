package uz.gita.client.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.client.R

class ProductScreenImageAdapter : RecyclerView.Adapter<ProductScreenImageAdapter.Holder>() {
    private val imageList:ArrayList<String> = arrayListOf()
    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_description, null))

    override fun onBindViewHolder(holder: Holder, position: Int) =holder.bind()

    override fun getItemCount(): Int = imageList.size
}
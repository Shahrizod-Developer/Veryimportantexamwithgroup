package uz.gita.client.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.client.databinding.ItemOrderBinding

class OrdersAdapter : RecyclerView.Adapter<OrdersAdapter.VH>() {
    private val oldList: ArrayList<OrderData> = arrayListOf()

    fun submitList(newList: ArrayList<OrderData>) {
        oldList.clear()
        oldList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class VH(private val itemBinding: ItemOrderBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind() {
            val item = oldList[absoluteAdapterPosition]
            itemBinding.tvItemCount.text = item
            itemBinding.tvItemName.text = item
            itemBinding.orderStatus.text = item
            itemBinding.tvItemPrice.text = item
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return oldList.size
    }

}
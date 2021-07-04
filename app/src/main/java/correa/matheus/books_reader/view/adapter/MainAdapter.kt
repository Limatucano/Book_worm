package correa.matheus.books_reader.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import correa.matheus.books_reader.R
import correa.matheus.books_reader.model.DataBook


class MainAdapter(private val items: List<DataBook>, var clickListener: OnClickItemListener) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_books_item,parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.initialize(items.get(position),clickListener)
        holder.bindView(item)
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name_book = itemView.findViewById<TextView>(R.id.name_book)
        val autor = itemView.findViewById<TextView>(R.id.autor)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        fun bindView(item: DataBook) = with(itemView){

            item?.let{
                autor.text = item.authors.joinToString()
                name_book.text = item.title
            }

        }
        fun initialize(item: DataBook, action: OnClickItemListener){
            name_book.text = item.title

            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }
    }

    interface OnClickItemListener{
        fun onItemClick(items: DataBook, position: Int)
    }

}
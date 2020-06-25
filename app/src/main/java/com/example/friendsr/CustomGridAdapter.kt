package com.example.friendsr

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView


class CustomGridAdapter(aContext: Context, listData: List<User>) :
    BaseAdapter() {
    private val listData: List<User>
    private val layoutInflater: LayoutInflater
    private val context: Context
    override fun getCount(): Int {
        return listData.size
    }

    override fun getItem(position: Int): Any {
        return listData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView: View? = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item_layout, null)
            holder = ViewHolder()
            holder.image = convertView.findViewById(R.id.userImage) as ImageView
            holder.name =
                convertView.findViewById(R.id.userName)
            holder.rating = convertView.findViewById(R.id.rating)
            convertView.setTag(holder)
        } else {
            holder = convertView.getTag() as ViewHolder
        }
        val user: User = listData[position]
        holder.name?.setText(user.name)
        val imageId = getMipmapResIdByName(user.image)
        holder.image?.setImageResource(imageId)
        holder.rating!!.rating = user.rating
        return convertView
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    fun getMipmapResIdByName(resName: String): Int {
        val pkgName: String = context.getPackageName()

        // Return 0 if not found.
        val resID: Int = context.getResources().getIdentifier(resName, "mipmap", pkgName)
        Log.i("CustomGridView", "Res Name: $resName==> Res ID = $resID")
        return resID
    }

    internal class ViewHolder {
        var image: ImageView? = null
        var name: TextView? = null
        var rating: RatingBar? = null
    }

    init {
        context = aContext
        this.listData = listData
        layoutInflater = LayoutInflater.from(aContext)
    }
}
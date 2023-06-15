/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.basicui.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.basicui.R
import com.example.basicui.presentation.theme.BasicUITheme
import android.content.Context
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.content.Intent


class MainActivity : ComponentActivity() {

    private val data = ArrayList<String>()

    private fun generateListContent() {
        val items = listOf("Profile", "Analytics", "Chat", "Settings")
        for (item in items) {
            data.add(item)
        }
    }

    private class MyListAdapter(context: Context, private val layout: Int, private val objects: List<String>) :
        ArrayAdapter<String>(context, layout, objects) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var mainViewHolder: ViewHolder? = null
            var convertView = convertView
            if (convertView == null) {
                val inflater = LayoutInflater.from(context)
                convertView = inflater.inflate(layout, parent, false)
                val viewHolder = ViewHolder()
                viewHolder.thumbnail = convertView.findViewById(R.id.list_item_thumbnail)
                viewHolder.button = convertView.findViewById(R.id.list_item_btn)
                convertView.tag = viewHolder
            }
            mainViewHolder = convertView?.tag as ViewHolder
            mainViewHolder.button?.setOnClickListener {
                //Toast.makeText(context, "Button was clicked for list item $position", Toast.LENGTH_SHORT).show()
                val intent = when (position) {
                    0 -> Intent(context, ProfileActivity::class.java)
                    1 -> Intent(context, AnalyticsActivity::class.java)
                    2 -> Intent(context, ChatActivity::class.java)
                    3 -> Intent(context, SettingsActivity::class.java)
                    else -> null
                }
                intent?.let {
                    context.startActivity(it)
                    Toast.makeText(context, "Button was clicked for list item $position", Toast.LENGTH_SHORT)
                }
            }
            mainViewHolder.button?.text = getItem(position)
            return convertView
        }
    }

    class ViewHolder {
        var thumbnail: ImageView? = null
        var button: Button? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) { //main function
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val lv: ListView = findViewById(R.id.listview)
        generateListContent()
        lv.adapter = MyListAdapter(this, R.layout.list_item, data)
        lv.onItemClickListener = AdapterView.OnItemClickListener { _, view, position, _ ->
            val intent = when (position) {
                0 -> Intent(this@MainActivity, ProfileActivity::class.java)
                1 -> Intent(this@MainActivity, AnalyticsActivity::class.java)
                2 -> Intent(this@MainActivity, ChatActivity::class.java)
                3 -> Intent(this@MainActivity, SettingsActivity::class.java)
                else -> null
            }
            intent?.let {
                startActivity(it)
            }
        }

    }//end of main code





}//end of MainActivity

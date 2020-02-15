package ekh.challenge.barksky

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt

@BindingAdapter("android:text")
fun setText(textView: TextView, value: Double){
    val stringValue = value.roundToInt().toString()
    textView.text = stringValue
}

@BindingAdapter("app:temperature")
fun setTextAsTemp(textView: TextView, value: Double){
    val stringValue = value.roundToInt().toString()
    textView.text = stringValue +"\u00B0"
}

@BindingAdapter("app:srcCompat")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}
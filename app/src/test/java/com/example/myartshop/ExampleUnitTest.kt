package com.example.myartshop

import com.example.myartshop.data.Category
import com.example.myartshop.data.DataSource
import com.example.myartshop.data.Photo
import com.example.myartshop.ui.OrderViewModel
import com.example.myartshop.ui.ui.calculateTotalPrice
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ArtShopUnitTest {
    @Test
    fun total_title1_wood_10mm_small() {
        val ActualFrameSum = calculateTotalPrice(frameType="Wood", frameWidth = "10 mm", photoSize = "Small")
        val photo = Photo(1, R.string.title_1, R.drawable.image1, DataSource.listOfartists[1], Category.Nature, 125.0f)
        val expectedFrameSum = 50f+10f+20f
        val expectedTotal = photo.price + expectedFrameSum
        val actualTotal = photo.price + ActualFrameSum
        assertEquals(expectedFrameSum, ActualFrameSum)
        assertEquals(expectedTotal, actualTotal)
    }

    @Test
    fun total_title1_metal_15mm_medium() {
        val actualFrameSum = calculateTotalPrice(frameType="Metal", frameWidth = "15 mm", photoSize = "Medium")
        val photo = Photo(1, R.string.title_1, R.drawable.image1, DataSource.listOfartists[1], Category.Nature, 125.0f)
        val expectedFrameSum = 60f+20f+30f
        val expectedTotal = photo.price + expectedFrameSum
        val actualTotal = photo.price + actualFrameSum
        assertEquals(expectedFrameSum, actualFrameSum)
        assertEquals(expectedTotal, actualTotal)
    }

    @Test
    fun total_title1_plastic_20mm_large() {
        val actualFrameSum = calculateTotalPrice(frameType="Plastic", frameWidth = "20 mm", photoSize = "Large")
        val photo = Photo(1, R.string.title_1, R.drawable.image1, DataSource.listOfartists[1], Category.Nature, 125.0f)
        val expectedFrameSum = 40f+30f+40f
        val expectedTotal = photo.price + expectedFrameSum
        val actualTotal = photo.price + actualFrameSum
        assertEquals(expectedFrameSum, actualFrameSum)
        assertEquals(expectedTotal, actualTotal)
    }
}
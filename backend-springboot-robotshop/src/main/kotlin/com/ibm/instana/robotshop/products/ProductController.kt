package com.ibm.instana.robotshop.products

import com.ibm.instana.robotshop.models.ProductItem
import com.ibm.instana.robotshop.constants.Constants.VERSION_NUMBER_V1
import com.ibm.instana.robotshop.utils.ServiceUtil
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import kotlin.collections.ArrayList

@RestController
@RequestMapping("/$VERSION_NUMBER_V1/product")
class ProductController(private val jsonFileReaderService: ServiceUtil) {

    @GetMapping("/all")
    @ResponseBody
    fun getAllProducts(): ArrayList<ProductItem> {
        val listItems = jsonFileReaderService.readJsonFile("products.json")
        listItems.shuffle()
        return listItems
    }

    @GetMapping("/{id}")
    fun getProductWithId(@PathVariable id: Int): ProductItem? {
        return jsonFileReaderService.readJsonFile("products.json").find { it.id == id }
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id $id not found")
    }

    @GetMapping("/categories")
    fun getProductCategories():ArrayList<String>{
        return jsonFileReaderService.readJsonFile("products.json").map { it.category }
                .distinct()
                .toCollection(ArrayList())
    }

    @GetMapping("/categories/{category}")
    fun getProductWithCategories(@PathVariable category:String):List<ProductItem>{
        return jsonFileReaderService.readJsonFile("products.json").filter { it.category == category }
    }


}
# 🚀 Awesome EUM Robotshop Backend APIs

APIs for a robotshop e-commerce application

# 📁 Collection: V2 

#### port default points to 8081 
```plain
Base URL: http://localhost:8081
```

## End-point: v2/payment/validate
### Method: POST
> ```plain
>localhost:8081/v2/payment/validate
>```
### Body (**raw**)

```json
{
    "cardNumber": "",
    "cvv": "",
    "expiryYear": ""
}
```

### Response: 200
```json
{
    "message": "Service API call result: Success Success Success"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v2/banner/all
### Method: GET
> ```plain
>localhost:8081/v2/banner/all
>```
### Response: 200
```json
[
    {
        "backgroundImageUrl": "/images/banner1.jpeg",
        "itemImageUrl": "/images/banner_item1.png",
        "bannerTitle": "Up to 25% Off",
        "bannerSubTitle": "iPhone 15 Pro Max"
    },
    {
        "backgroundImageUrl": "/images/banner2.jpeg",
        "itemImageUrl": "/images/banner_item2.jpeg",
        "bannerTitle": "Cashback",
        "bannerSubTitle": "35% cashback on JBL headset"
    },
    {
        "backgroundImageUrl": "/images/banner3.jpeg",
        "itemImageUrl": "/images/banner_item3.png",
        "bannerTitle": "Free Coupons",
        "bannerSubTitle": "Buy 4 electronic items, get coupons worth $350"
    }
]
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v2/product/all
### Method: GET
> ```plain
>localhost:8081/v2/product/all
>```
### Response: 200
```json
[
    {
        "category": "jewelery",
        "description": "•Contains : 1 Necklace + 1 Pair of Earring + 1 Maangtikka\n•Dimension : Necklace Length - 18 cm| Necklace Width - 12 cm| Earring Length - 5 cm| Earring Width - 3 cm| Maangtikka Length - 12 cm| Maangtikka Width - 3 cm",
        "id": 6,
        "image": "/images/jewelery_01.jpg",
        "price": 168,
        "rating": {
            "count": 70,
            "rate": 3.9
        },
        "title": "Sukkhi Trendy Kundan Gold Plated Wedding Jewellery Pearl"
    },
    {
        "category": "electronics",
        "description": "21.5 inches Full HD (1920 x 1080) widescreen IPS display And Radeon free Sync technology. No compatibility for VESA Mount Refresh Rate: 75Hz - Using HDMI port Zero-frame design | ultra-thin | 4ms response time | IPS panel Aspect ratio - 16: 9. Color Supported - 16. 7 million colors. Brightness - 250 nit Tilt angle -5 degree to 15 degree. Horizontal viewing angle-178 degree. Vertical viewing angle-178 degree 75 hertz",
        "id": 13,
        "image": "/images/electronic_05.jpg",
        "price": 599,
        "rating": {
            "count": 250,
            "rate": 2.9
        },
        "title": "Acer EK220Q E3 21.5 Inch IPS Full HD (1920x1080) Backlit LED LCD Monitor"
    },
    //....
    {
        "category": "women's clothing",
        "description": "95%Cotton,5%Spandex, Features: Casual, Short Sleeve, Letter Print,V-Neck,Fashion Tees, The fabric is soft and has some stretch., Occasion: Casual/Office/Beach/School/Home/Street. Season: Spring,Summer,Autumn,Winter.",
        "id": 20,
        "image": "/images/women_cloth_06.jpeg",
        "price": 12.99,
        "rating": {
            "count": 145,
            "rate": 3.6
        },
        "title": "DANVOUY Womens T Shirt Casual Cotton Short"
    }
]
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v2/product/{id}
### Method: GET
> ```plain
>localhost:8081/v2/product/1
>```
### Response: 200
```json
{
    "category": "electronics",
    "description": "Processor: Intel Celeron N4500 processor, base speed 1.1 Ghz, max speed 2.8 Ghz, 2 Cores, 4MB L3 smart cache | Memory: 8GB DDR4 RAM 2933 MHz, dual-channel capable | Storage: 256GB SSD M.2 upgradable up to 512GB SSD \nOperating System: Preloaded Windows 11 Home SL with Lifetime Validity \nDisplay: 15.6 (39.62 cm) screen with (1920x1080) FHD Antiglare, 250 Nits display | Graphics: Intel UHD Graphics comes with DirectX 12 enables amazing graphics | Monitor Supports: Supports up to 2 independent displays",
    "id": 1,
    "image": "/images/lenovo_01.jpeg",
    "price": 299.95,
    "rating": {
        "count": 120,
        "rate": 3.9
    },
    "title": "Lenovo V15 Intel Celeron N4500 15.6 (39.62 cm)"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v2/product/categories
### Method: GET
> ```plain
>localhost:8081/v2/product/categories
>```
### Response: 200
```json
[
    "electronics",
    "men's clothing",
    "jewelery",
    "women's clothing"
]
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v2/product/categories/{categories}
### Method: GET
> ```plain
>localhost:8081/v2/product/categories/electronics
>```
### Response: 200
```json
[
    {
        "category": "electronics",
        "description": "Processor: Intel Celeron N4500 processor, base speed 1.1 Ghz, max speed 2.8 Ghz, 2 Cores, 4MB L3 smart cache | Memory: 8GB DDR4 RAM 2933 MHz, dual-channel capable | Storage: 256GB SSD M.2 upgradable up to 512GB SSD \nOperating System: Preloaded Windows 11 Home SL with Lifetime Validity \nDisplay: 15.6 (39.62 cm) screen with (1920x1080) FHD Antiglare, 250 Nits display | Graphics: Intel UHD Graphics comes with DirectX 12 enables amazing graphics | Monitor Supports: Supports up to 2 independent displays",
        "id": 1,
        "image": "/images/lenovo_01.jpeg",
        "price": 299.95,
        "rating": {
            "count": 120,
            "rate": 3.9
        },
        "title": "Lenovo V15 Intel Celeron N4500 15.6 (39.62 cm)"
    },
    //...
    {
        "category": "electronics",
        "description": "32 inch Samsung Monitor - QHD 2560 x 1440 Resolution | VA Panel Monitor\n178° Horizontal and Vertical Viewing Angle for wider and clear view\n240 Hz Refresh rate | 16:9 Aspect Ratio | 350cd/m2 Brightness (Typical)",
        "id": 14,
        "image": "/images/electronic_06.jpg",
        "price": 999.99,
        "rating": {
            "count": 140,
            "rate": 2.2
        },
        "title": "Samsung 32-inch(81.28 cm) QHD Curved Odyssey G6 Gaming Monitor, 240 Hz"
    }
]
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v2/user/validate
### Method: POST
> ```plain
>localhost:8081/v2/user/validate
>```
### Headers

|Content-Type|Value|
|---|---|
|Content-Type|application/json|


### Body (**raw**)

```json
{
    "name":"",
    "email":"",
    "password":""
}
```

### Response: 200
```json
{
    "message": "Success"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v2/user/create
### Method: POST
> ```plain
>localhost:8081/v2/user/create
>```
### Headers

|Content-Type|Value|
|---|---|
|Content-Type|application/json|


### Body (**raw**)

```json
{
    "name":"",
    "email":"",
    "password":""
}
```

### Response: 400
```json
{
    "message": "Empty User Name or Password"
}
```

### Response: 200
```json
{
    "userName": "dsfs",
    "userEmail": "null",
    "userId": "7251830",
    "createdAt": "1719246505899"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v2/notifications/all
### Method: GET
> ```plain
>localhost:8081/v2/notifications/all
>```
### Headers

|Content-Type|Value|
|---|---|
|Content-Type|application/json|


### Response: 200
```json
[
    {
        "title": "Order Dispatched",
        "subTitle": "Your order ID : #1234DER has been dispatched",
        "dateTime": "1718607896000",
        "complete": false
    },
    {
        "title": "Item Delivered",
        "subTitle": "Your order ID : #54324D4H has been delivered",
        "dateTime": "1707886476000",
        "complete": false
    },
    {
        "title": "Item Packed",
        "subTitle": "Your order ID : #KNTDIF5 is packed for Dispatch",
        "dateTime": "1710917656000",
        "complete": false
    },
    {
        "title": "Item Delivered",
        "subTitle": "Your order ID : #1234DER is packed for Dispatch",
        "dateTime": "1718348056000",
        "complete": true
    }
]
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v2/discount/coupon
### Method: POST
> ```plain
>localhost:8081/v2/discount/coupon
>```
### Body (**raw**)

```json
{
    "couponId":"couponId",
    "cartValue":99.33,
    "userId":"Something",
    "percentage":23.4
}
```

### Response: 200
```json
{
    "couponApplicable": true,
    "applicableValue": 1.23
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
# 📁 Collection: V1 


## End-point: v1/payment/validate
### Method: POST
> ```plain
>localhost:8081/v1/payment/validate
>```
### Headers

|Content-Type|Value|
|---|---|
|Content-Type|application/json|


### Body (**raw**)

```json
{
    "cardNumber": "",
    "cvv": "",
    "expiryYear": ""
}
```

### Response: 500
```json
{
    "timestamp": "2024-06-24T16:12:51.091+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "trace": "r fit, delivers a sleek, more feminine silhouette and Added Comfort",
        "id": 19,
        "image": "/images/women_cloth_05.jpg",
        "price": 7.95,org.springframework.web.client.HttpServerErrorException$InternalServerError: 500 : \"{\"timestamp\":\"2024-06-24T16:12:51.082+00:00\",\"status\":500,\"error\":\"Internal Server Error\",\"trace\":\"org.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63)\n\tat java.base/java.lang.Thread.run(Thread.java:1583)\n",
    "message": "500 : \"{\"timestamp\":\"2024-06-24T16:12:51.082+00:00\",\"status\":500,\"error\":\"Internal Server Error\",\"trace\":\"org.springframework.web.client.HttpClientErrorException$NotFound: 404 : \\\"{\\\"timestamp\\\":\\\"2024-06-24T16:12:51.050\\\"\",\"path\":\"/v1/payment/gateway\"}\"",
    "path": "/v1/payment/validate"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v1/banner/all
### Method: GET
> ```plain
>localhost:8081/v1/banner/all
>```
### Response: 200
```json
[
    {
        "backgroundImageUrl": "/images/banner1.jpeg",
        "itemImageUrl": "/images/banner_item1.png",
        "bannerTitle": "Up to 25% Off",
        "bannerSubTitle": "iPhone 15 Pro Max"
    },
    {
        "backgroundImageUrl": "/images/banner2.jpeg",
        "itemImageUrl": "/images/banner_item2.jpeg",
        "bannerTitle": "Cashback",
        "bannerSubTitle": "35% cashback on JBL headset"
    },
    {
        "backgroundImageUrl": "/images/banner3.jpeg",
        "itemImageUrl": "/images/banner_item3.png",
        "bannerTitle": "Free Coupons",
        "bannerSubTitle": "Buy 4 electronic items, get coupons worth $350"
    }
]
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v1/product/all
### Method: GET
> ```plain
>localhost:8081/v1/product/all
>```
### Response: 200
```json
[
    {
        "category": "men's clothing",
        "description": "FLATTERING TEES: Our premium mens t-shirts packs are made from our signature fabric blend and made to feel comfortable while looking good all day, every day.\nMODERN FIT: Our t-shirt multipack will keep you looking good every day of the week. It's modern fit is designed to look good on every build. These t-shirts highlight your arms and shoulders and have a flattering drape at the midsection.\nULTRA-SOFT: Our multipack of men's tshirts are made from our signature poly-cotton blend is lightweight and breathable, making it the perfect tee for any occasion.",
        "id": 2,
        "image": "/images/mens_t_shirt_01.jpg",
        "price": 22.3,
        "rating": {
            "count": 259,
            "rate": 4.1
        },
        "title": "INTO THE AM Mens T Shirt Packs - Short Sleeve"
    },
    {
        "category": "electronics",
        "description": "Processor: Intel Celeron N4500 processor, base speed 1.1 Ghz, max speed 2.8 Ghz, 2 Cores, 4MB L3 smart cache | Memory: 8GB DDR4 RAM 2933 MHz, dual-channel capable | Storage: 256GB SSD M.2 upgradable up to 512GB SSD \nOperating System: Preloaded Windows 11 Home SL with Lifetime Validity \nDisplay: 15.6 (39.62 cm) screen with (1920x1080) FHD Antiglare, 250 Nits display | Graphics: Intel UHD Graphics comes with DirectX 12 enables amazing graphics | Monitor Supports: Supports up to 2 independent displays",
        "id": 1,
        "image": "/images/lenovo_01.jpeg",
        "price": 299.95,
        "rating": {
            "count": 120,
            "rate": 3.9
        },
        "title": "Lenovo V15 Intel Celeron N4500 15.6 (39.62 cm)"
    },
    //....
    {
        "category": "electronics",
        "description": "Expand your PS4 gaming experience, Play anywhere Fast and easy, setup Sleek design with high capacity, 3-year manufacturer's limited warranty",
        "id": 12,
        "image": "/images/electronic_04.jpg",
        "price": 114,
        "rating": {
            "count": 400,
            "rate": 4.8
        },
        "title": "WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive"
    }
]
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v1/product/{id}
### Method: GET
> ```plain
>localhost:8081/v1/product/1
>```
### Response: 200
```json
{
    "category": "electronics",
    "description": "Processor: Intel Celeron N4500 processor, base speed 1.1 Ghz, max speed 2.8 Ghz, 2 Cores, 4MB L3 smart cache | Memory: 8GB DDR4 RAM 2933 MHz, dual-channel capable | Storage: 256GB SSD M.2 upgradable up to 512GB SSD \nOperating System: Preloaded Windows 11 Home SL with Lifetime Validity \nDisplay: 15.6 (39.62 cm) screen with (1920x1080) FHD Antiglare, 250 Nits display | Graphics: Intel UHD Graphics comes with DirectX 12 enables amazing graphics | Monitor Supports: Supports up to 2 independent displays",
    "id": 1,
    "image": "/images/lenovo_01.jpeg",
    "price": 299.95,
    "rating": {
        "count": 120,
        "rate": 3.9
    },
    "title": "Lenovo V15 Intel Celeron N4500 15.6 (39.62 cm)"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v1/product/categories
### Method: GET
> ```plain
>localhost:8081/v1/product/categories
>```
### Response: 200
```json
[
    "electronics",
    "men's clothing",
    "jewelery",
    "women's clothing"
]
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v1/product/categories/{categories}
### Method: GET
> ```plain
>localhost:8081/v1/product/categories/electronics
>```
### Response: 200
```json
[
    {
        "category": "electronics",
        "description": "Processor: Intel Celeron N4500 processor, base speed 1.1 Ghz, max speed 2.8 Ghz, 2 Cores, 4MB L3 smart cache | Memory: 8GB DDR4 RAM 2933 MHz, dual-channel capable | Storage: 256GB SSD M.2 upgradable up to 512GB SSD \nOperating System: Preloaded Windows 11 Home SL with Lifetime Validity \nDisplay: 15.6 (39.62 cm) screen with (1920x1080) FHD Antiglare, 250 Nits display | Graphics: Intel UHD Graphics comes with DirectX 12 enables amazing graphics | Monitor Supports: Supports up to 2 independent displays",
        "id": 1,
        "image": "/images/lenovo_01.jpeg",
        "price": 299.95,
        "rating": {
            "count": 120,
            "rate": 3.9
        },
        "title": "Lenovo V15 Intel Celeron N4500 15.6 (39.62 cm)"
    },
    //...
    {
        "category": "electronics",
        "description": "32 inch Samsung Monitor - QHD 2560 x 1440 Resolution | VA Panel Monitor\n178° Horizontal and Vertical Viewing Angle for wider and clear view\n240 Hz Refresh rate | 16:9 Aspect Ratio | 350cd/m2 Brightness (Typical)",
        "id": 14,
        "image": "/images/electronic_06.jpg",
        "price": 999.99,
        "rating": {
            "count": 140,
            "rate": 2.2
        },
        "title": "Samsung 32-inch(81.28 cm) QHD Curved Odyssey G6 Gaming Monitor, 240 Hz"
    }
]
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v1/user/validate
### Method: POST
> ```plain
>localhost:8081/v1/user/validate
>```
### Body (**raw**)

```json
{
    "name":"",
    "email":"",
    "password":""
}
```

### Response: 200
```json
{
    "message": "Success"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v1/user/create
### Method: POST
> ```plain
>localhost:8081/v2/user/create
>```
### Headers

|Content-Type|Value|
|---|---|
|Content-Type|application/json|


### Body (**raw**)

```json
{
    "name":"",
    "email":"",
    "password":""
}
```

### Response: 400
```json
{
    "message": "Empty User Name or Password"
}
```

### Response: 200
```json
{
    "userName": "csa",
    "userEmail": "null",
    "userId": "3978979",
    "createdAt": "1719246563180"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v1/notifications/all
### Method: GET
> ```plain
>localhost:8081/v1/notifications/all
>```
### Headers

|Content-Type|Value|
|---|---|
|Content-Type|application/json|


### Response: 200
```json
[
    {
        "title": "Order Dispatched",
        "subTitle": "Your order ID : #1234DER has been dispatched",
        "dateTime": "1718607896000",
        "complete": false
    },
    {
        "title": "Item Delivered",
        "subTitle": "Your order ID : #54324D4H has been delivered",
        "dateTime": "1707886476000",
        "complete": false
    },
    {
        "title": "Item Packed",
        "subTitle": "Your order ID : #KNTDIF5 is packed for Dispatch",
        "dateTime": "1710917656000",
        "complete": false
    },
    {
        "title": "Item Delivered",
        "subTitle": "Your order ID : #1234DER is packed for Dispatch",
        "dateTime": "1718348056000",
        "complete": true
    }
]
```

### Response: 500
```json
Simulated 500 Internal Server Error
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: v1/discount/coupon
### Method: POST
> ```plain
>localhost:8081/v1/discount/coupon
>```
### Headers

|Content-Type|Value|
|---|---|
|Content-Type|application/json|


### Body (**raw**)

```json
{
    "couponId":"couponId",
    "cartValue":99.33,
    "userId":"Something",
    "percentage":23.4
}
```

### Response: 200
```json
{
    "couponApplicable": "true",
    "applicableValue": {
        "valueFirst": "some",
        "valueSecond": "thing"
    }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
_________________________________________________

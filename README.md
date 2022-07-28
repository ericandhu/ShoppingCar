# ShoppingCar 購物車
###### Servlet + JDBC + MySQL

# 取得資料
* 取得Cart全部資料 http://localhost:8080/ShoppingCar/ericaShoppingCar/index?inputKey=1
* 取得Cart指定資料 http://localhost:8080/ShoppingCar/ericaShoppingCar/index?cart_number=1&inputKey=2
* 取得CommodityPoolMain全部資料 http://localhost:8080/ShoppingCar/ericaShoppingCar/index?inputKey=3
* 取得CommodityPoolMain指定資料 http://localhost:8080/ShoppingCar/ericaShoppingCar/index?inputKey=4&cart_number=650

# 新增Cart單筆資料
#### 單筆新增 http://localhost:8080/ShoppingCar/insertShoppingCar/insert

	{
		"inputKey" : "1",
		"amount" : {{$randomInt}},
		"cart_number" : {{$randomInt}},
		"created_by" : "erica",
		"last_modified_by" : "erica",
		"customer" : "{{$guid}}"
	}
	
# 新增CommodityPoolMain單筆資料
#### 單筆新增	 http://localhost:8080/ShoppingCar/insertShoppingCar/insert

	{
	    "inputKey" : "3",
	    "commodity_pool_id" : {{$randomInt}},
	    "cart_number" : {{$randomInt}},
	    "commodity_pool_name" : "erica",
	    "commodity_pool_type" : "AA",
	    "stop_check" : "Y",
	    "stop_desc" : "Y",
	    "log_id" : "{{$randomInt}}"
	}
		
# 新增Cart全部資料
#### 多筆新增 http://localhost:8080/ShoppingCar/insertShoppingCar/insert
	
	{  
		"inputKey" : "2",
    		"cart_list" : 
    	[
	        { 
		        "amount" : {{$randomInt}},
		        "cart_number" : {{$randomInt}},
		        "created_by" : "erica",
		        "last_modified_by" : "erica",
		         "customer" : "{{$guid}}"
	        },
	        { 
		        "amount" : 500,
		        "cart_number" : {{$randomInt}},
		        "created_by" : "erica",
		        "last_modified_by" : "erica",
		         "customer" : "{{$guid}}"
	        },
	        { 
		        "amount" : 500,
		       "cart_number" : {{$randomInt}},
		        "created_by" : "erica",
		        "last_modified_by" : "erica",
		         "customer" : "{{$guid}}"
	        }
	    ]
	}
	
# 更新Cart資料
#### 單筆更新 http://localhost:8080/ShoppingCar/updateShoppingCar/update
	{
	    "inputKey" : "1",
	    "amount" : 600,
	    "cart_number" : "2",
	    "created_by" : "erica123",
	    "last_modified_by" : "erica123",
	    "customer" : "{{$guid}}"
	}

# 更新CommodityPoolMain資料
#### 單筆更新 http://localhost:8080/ShoppingCar/updateShoppingCar/update?inputKey=3
	{
    "inputKey" : "3",
    "cart_number" : "650",
    "commodity_pool_id" :"123" ,
    "commodity_pool_name" : "erica",
    "commodity_pool_type" : "AA",
    "stop_check" : "Y",
    "stop_desc" : "Y",
    "log_id" : "234"

    }

# 刪除Cart資料
#### 單筆刪除 http://localhost:8080/ShoppingCar/deleteShoppingCar/delete
	{
	    "inputKey" : "1",
	    "cart_number" : "2"
	}
	
	

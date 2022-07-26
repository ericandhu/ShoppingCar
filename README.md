#ShoppingCar 購物車

#URL 

#取得全部資料
	http://localhost:8080/ShoppingCar/ericaShoppingCar/index?inputKey=1

#取得指定資料
	http://localhost:8080/ShoppingCar/ericaShoppingCar/index?cart_number=1&inputKey=

#新增單筆資料
	單筆新增
	http://localhost:8080/ShoppingCar/insertShoppingCar/insert
	
	{
		"inputKey" : "1",
		"amount" : {{$randomInt}},
		"cart_number" : {{$randomInt}},
		"created_by" : "erica",
		"last_modified_by" : "erica",
		"customer" : "{{$guid}}"
	}
		
#新增全部資料
	多筆新增
	http://localhost:8080/ShoppingCar/insertShoppingCar/insert
	
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
	
# 更新資料
	##單筆更新
	http://localhost:8080/ShoppingCar/updateShoppingCar/update
	{
	    "inputKey" : "1",
	    "amount" : 600,
	    "cart_number" : "2",
	    "created_by" : "erica123",
	    "last_modified_by" : "erica123",
	    "customer" : "{{$guid}}"
	}
	
# 刪除資料
	## 單筆刪除
	http://localhost:8080/ShoppingCar/deleteShoppingCar/delete
	{
	    "inputKey" : "1",
	    "cart_number" : "2"
	}
	
	
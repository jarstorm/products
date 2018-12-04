# products
Products REST API

## URLs
Get all products
GET: <host>/product

Create new product
PUT: <host>/product/create
Parameters: 
	price: Product price. Format XXXX.XX
	name: Product name
	
Update product
POST: <host>/product/update
	id: product id
	price: Product price. Format XXXX.XX
	name: Product name
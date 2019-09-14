var vm=new Vue({
	el:"#app",
	data:{
		totalMoney:0,
		productList:[],
		checkAllFlag:false
	},
	filters:{
		formatMoney: function (value) {
			return "￥ "+value.toFixed(2);
		}
	},
	mounted:function(){
		this.cartView();
	},
	methods:{
		cartView:function () {
			var _this=this;
			//获取商品数据信息
			this.$http.get("/cart/doObject").then(function (res) {
				_this.productList = res.body.list;
				
			})
		},
		//点击增加或减少商品数量按钮后修改对应金额
		changeMoney:function (product,way) {
	 
			if (way>0) {
				product.number++;
				//提交数据
				this.$http.get("/cart/doUpdateNumber?id="+product.id+"&status=1").then(function (item) {
				})
			}else{
				if (way==-1 && product.number>1) {
					//提交数据
					this.$http.get("/cart/doUpdateNumber?id="+product.id+"&status=0").then(function (item) {
					})
				}
				
				product.number--;
				 	if (product.number<1) {
					product.number=1;
				}
			}
			this.getTotalMoney();
		},
		//单选框设置
		checkBox:function (item){
			var _this=this;
			var num=0;
			if (typeof item.check == "undefined") {
				this.$set(item,"check",true);
			}else{
				item.check = !item.check;
			}
			this.productList.forEach(function (item,value) {
				if (item.check) {
					num++;
				}
			})
			if (num==_this.productList.length) {
				this.checkAllFlag=true;
			}else{
				this.checkAllFlag=false;
			}
			this.getTotalMoney();
		},
		//全选按钮设置
		checkAll:function (){
			var _this=this;
			this.checkAllFlag = !this.checkAllFlag;
			this.productList.forEach(function(item,index){
				if (typeof item.check == "undefined") {
					_this.$set(item,"check",_this.checkAllFlag);
				}else{
					item.check = _this.checkAllFlag;
				}
			})
			this.getTotalMoney();
		},
		

		//总金额设置
		getTotalMoney:function () {
			var _this=this;
			this.totalMoney = 0;
			this.productList.forEach(function (item,index) {
				if (item.check) {
					_this.totalMoney += item.number*item.goodsprice;
				}
			})
		},
		
		//结账
		pay:function (){
			var _this=this;
			var param = new Array();
			this.productList.forEach(function(item,index){
				if(item.check)
					{
					param.unshift(item.id);
 					}
			//	alert(index);
			})
			if (param.length>0)
				{
				//提交结算数据
				this.$http.get("/cart/doInsertOrder?param="+param).then(function (result) {
					layer.msg(result.body.msg);
					setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
						window.location.reload();//页面刷新
						},2000);
				})
				
				}
		},
		
		
		//支付宝结账
		zfbpay:function (){
			var _this=this;
			var param = new Array();
			this.productList.forEach(function(item,index){
				if(item.check)
					{
					param.unshift(item.id);
 					}
			})
			
			
			if (param.length>0)
				{
				//提交结算数据
				postUrl('/alipay/doPay',param);
				}
			
			
	 
		},
		
 
		
		//删除商品
		del: function (item) {
			var _this=this;
			//提交数据
			this.$http.get("/cart/doDeleteObject?id="+item.id).then(function (item) {
				layer.msg(item.body.msg);
				setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
					window.location.reload();//页面刷新
					},2000);
			})
 			var index=this.productList.indexOf(item);
			this.productList.splice(index,1);
			this.getTotalMoney();
		}
	}
})


//以post形式提交
function postUrl(url,param){
	  var f=document.createElement("form");
	  f.target="_blank";
	  f.action=url;
	  f.method="post";//指定为post
	  f.innerHTML="<input type='hidden' name='param' value='"+param+"'/>";
	  document.body.appendChild(f);  
	 f.submit()  
	   
}


 
 
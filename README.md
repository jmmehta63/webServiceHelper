Webservice helper
==================

This is a fork of Webservice helper from David Kingma.

Original author : Jignesh M. Mehta

1. What does the webservice helper?

-> The webservice helper does what the name suggest: helping you to making web service request in android. just you need to enter your url address and if there is a postmethod then add respected parameters.
-> Easy to understand and easy to use for all the android developer.
-> Once you add this files and we can use it any where in entire application.


2. How to use it?
->  Follow steps and you can achive it.

i) Make a object of webHelper Class like, 

       webserviceHelper helper = new webserviceHelper(this);

ii) If you want to show Processbar then set isShowProgressbar true.like,
      
       helper.isShowProgressbar(true);
else you can set it false or not initialize anything.

iii) If you want get request then you can directly call callGetAPI() and set parameters URL and CALLBACK method which can impliment success and failure method that you want to override. like,


        helper.callGetAPI(url, new webserviceHelper.WebServiceCallback() {
                @Override
                public void onSuccess(String response) {
			//code while success                    
                }

                @Override
                public void onFailure(String errorMessage) {
			//code while failure.

                }
            });
iV) If you want to use Post method  then we want to pass URL,Parameters in Json Format and CALLBACK method which can impliment success and failure method that you want to override. like,
 

        helper.callPostAPI(url, jobj.toString(), new webserviceHelper.WebServiceCallback() {

                @Override
                public void onSuccess(String response) {
			//code while success                    
                }

                @Override
                public void onFailure(String errorMessage) {
			//code while failure.

                }
            });

NOTE: 

i) Use this method inside try catch so no crash issue during fail to call webservice. 
ii) please use processbar while you not want async call. 


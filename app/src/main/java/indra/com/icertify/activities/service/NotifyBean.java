package indra.com.icertify.activities.service;

import android.os.Message;
import android.util.Log;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import indra.com.icertify.activities.handler.ServiceHandler;
import indra.com.icertify.activities.service.external.PreferenceCallBack;


public class NotifyBean
{
	static Map<Integer, ServiceHandler> serviceHandlers = Collections.synchronizedMap(new HashMap<Integer,ServiceHandler>());
	static Map<Integer, PreferenceCallBack> preferenceCallBacks =  Collections.synchronizedMap(new HashMap<Integer,PreferenceCallBack>());
	
	/**
	 * 
	 * @param force
	 * @param preferenceCallBack
	 * @return
	 */
	
	public static void createHandle(int idNotify, ServiceHandler serviceHandler)
	{	
		
		
		if(serviceHandlers.size() > 0)
		{
			if(serviceHandlers.get(idNotify) == null )
				serviceHandlers.put(idNotify ,serviceHandler);
		}
		else
		{
			serviceHandlers.put(idNotify ,serviceHandler);
		}
	}
	
	/**
	 * 
	 * @param idNotify
	 * @param preferenceCallBack
	 */
	
	public static void createEvent(int idNotify, PreferenceCallBack preferenceCallBack)
	{
		preferenceCallBacks.put(idNotify ,preferenceCallBack);
	}
	
	/**
	 * 
	 */
	
	public static void removeEvent(int idNotify)
	{	
		preferenceCallBacks.remove(idNotify);
	}

	public static void clean()
	{
		serviceHandlers.clear();
		preferenceCallBacks.clear();
	}
	
	/**
	 * 
	 * @param message
	 */
	
	public static void  notifyHandler(int idHandler, Message message)
	{
		serviceHandlers.get(idHandler).sendMessage(message);
	}
	
	/**
	 * 
	 * @param idEvent
	 * @param message
	 */
	
	public static void  notifyEvent(int idEvent)
	{
		Log.d("############", preferenceCallBacks.toString());
		Log.d("############", "" +preferenceCallBacks.size());
		preferenceCallBacks.get(idEvent).returnServiceResponse();
	}
	
	public static PreferenceCallBack getEvent(int idEvent)
	{
		Log.d("############", preferenceCallBacks.toString());
		Log.d("############", "" +preferenceCallBacks.size());
		return preferenceCallBacks.get(idEvent);
	}
	
	public static void  notifyEvent(int idEvent, Message message)
	{
		Log.d("############", preferenceCallBacks.toString());
		Log.d("############", "" +preferenceCallBacks.size());
		preferenceCallBacks.get(idEvent).returnServiceResponse(message);
	}
	
}

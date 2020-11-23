package com.imonfyre.dailyspells.app_code;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import android.util.Log;

public class Serializer
{

	public static byte[] SerializePC(PlayerCharacter convert)
	{
		return serialData(convert);
	}

	public static PlayerCharacter DeserializePC(byte[] convert)
	{
		return (PlayerCharacter)deserialData(convert);
	}
	
	private static byte[] serialData(Object obj)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try
		{
			ObjectOutput OS = new ObjectOutputStream(bos);
			OS.writeObject(obj);
			OS.close();
		}
		catch (IOException ioe)
		{
			Log.e("serialize of newPC", "error", ioe);
		}

		return bos.toByteArray();
	}
	
	private static Object deserialData(byte[] data)
	{
		try
		{
			ObjectInputStream OIS = new ObjectInputStream(new ByteArrayInputStream(data));
			Object converted = OIS.readObject();
			OIS.close();
			return converted;
		}
		catch (ClassNotFoundException cnfe)
		{
			Log.e("deserializeObject", "class not found error", cnfe);
			return null;
		}
		catch (IOException ioe)
		{
			Log.e("serialize of newPC", "error", ioe);
		}
		return null;
		
	}
}

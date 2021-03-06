package com.game.InputDevice;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import com.game.MessageHandler;
import com.game.MsgType;
import com.game.Vec2;
import com.game.MessageHandler.MsgReceiver;
import com.game.Scenes.PlayScene;

/**
 * Handles the input of a player using the touch screen
 * @author Ying
 *
 */
public class TouchInputDevice extends InputDevice 
{
	/**
	 * Creates the handler and links it to the appropriate one in PlayScene
	 * @param playScene to listen in
	 */
	public TouchInputDevice(PlayScene playScene) 
	{
		super(playScene);
		
		this.deviceHandler = new Handler()
		{			
			public void handleMessage(Message msg)
			{
				if(msg.what == MsgType.TOUCH_EVENT.ordinal())
				{
					MotionEvent event = (MotionEvent)msg.obj;
					Vec2 newPos = new Vec2(event.getX(),event.getY());
					
					MessageHandler.Get().Send(MsgReceiver.RENDERER, MsgType.REQUEST_WCS_TRANSFORM, newPos);

					
				}
				if(msg.what == MsgType.REPLY_WCS_TRANSFORM_REQUEST.ordinal())
				{
					parent.GetCursor().MoveTo((Vec2)msg.obj);
				}
			}
		};
		
		if(playScene.touchEvent != null)
		{
			Log.e("TouchInputDevice", "Touch handler is not null! Some other ID has already claimed it");
		}
		playScene.touchEvent = this.deviceHandler;
	}

	@Override
	public void Start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub

	}

}

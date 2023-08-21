package com.fastdownload.wallpaper.livetoolsexplore.livewallpaper;

import android.content.Context;
import android.content.SharedPreferences;
import android.opengl.GLSurfaceView;

import com.fastdownload.wallpaper.livetoolsexplore.livewallpaper.opengles.GLESPlaneAnimatedRenderer;


public class ColloredWallpaperService extends OpenGLESWallpaperService {

    private GLESPlaneAnimatedRenderer _mRenderer;


    @Override
    GLSurfaceView.Renderer getGLESRenderer() {
        _mRenderer = new GLESPlaneAnimatedRenderer(this);
        SharedPreferences prefs = getSharedPreferences("Info", Context.MODE_PRIVATE);
        String color = prefs.getString("color", "COLORFUL");
        _mRenderer.switchColors(color);
        Float animSpeed = prefs.getFloat("animSpeed", 0.2f);
        _mRenderer.changeAnimationSpeed(animSpeed);
        String motion = prefs.getString("motion", "straight");
        _mRenderer.changeMotion(motion);
        boolean sensors = prefs.getBoolean("sensors", false);
        _mEngine.activateSensors(sensors);
        return  _mRenderer;
    }
}

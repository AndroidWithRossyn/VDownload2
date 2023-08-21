package com.fastdownload.wallpaper.livetoolsexplore.interfaces;


import com.fastdownload.wallpaper.livetoolsexplore.model.FBStoryModel.NodeModel;
import com.fastdownload.wallpaper.livetoolsexplore.model.story.TrayModel;

public interface UserListInterface {
    void userListClick(int position, TrayModel trayModel);
    void fbUserListClick(int position, NodeModel trayModel);
}

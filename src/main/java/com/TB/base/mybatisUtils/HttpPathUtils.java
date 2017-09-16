package com.TB.base.mybatisUtils;

public class HttpPathUtils {
	  private static final String pre = "http://123.207.180.232:8080/Box/";
	  
	  /**
	   * 用户注册模块
	   * 提供的参数：password（密码），repassword（重复密码），phone（手机号）channelId（用户绑定时生成的）
	   * @return
	   */
	  public static String addUser(){
		  return pre+"user/addUser";
	  }

	  /**
	   * 创建角色模块
	   * 提供的参数：uid（注册完返回的uid），username（角色名），place（所在省份），constellation（星座），blood（血型）
	   * signature（个性签名），birthday（生日），hobby（爱好），job（工作），gender（性别），personalPassword（私人密码）
	   * age（年龄），ufacing（头像）
	   * @return
	   */
	  public static String createRole(){
		return pre+"user/createRole";  
	  }
	  
	  /**
	   * 修改私人密码
	   * 提供的参数：uid，personalPassword（修改后的私人密码）
	   * @return
	   */
	  public static String updatePersionalPassword(){
		  return pre+"user/updatePersionalPassword";
	  }
	  
	  /**
	   * 修改密码
	   * 提供的参数：uid,password
	   * @return
	   */
	  public static String updatePasswprd(){
		  return pre+"user/updatePasswprd";
	  }
	  
	  /**
	   * 查看用户信息
	   * 提供的参数：uid
	   * @return
	   */
	  public static String selectUser(){
		return pre+"user/selectUser";
		  
	  }
		 /**
		  * 查看角色的信息
		  * 提供的参数：uid 
		  * @return
		  */
	  public static String selectRole(){
		return pre+"user/selectRole";
		  
	  }
	  /**
	   * 修改用户信息
	   * 提供的参数：uid，phone，ufacing
	   * @return
	   */
	  public static String updateUserData(){
		return pre+"user/updateUserData";
		  
	  }
	  /**
	   * 修改角色信息
	 * 提供的参数：uid，username（角色名），place，constellation（星座），blood（血型）
	   * signature（个性签名），birthday（生日），hobby（爱好），job（工作），gender（性别），personalPassword（私人密码）
	   * age（年龄），ufacing（头像）
	   * @return
	   */
	  public static String updateRoleData(){
		  return pre+"user/updateRoleData";
	  }
	  
	  /**
	   * 登陆
	   * 提供的参数:number(登陆账号),password(登陆密码)，channelId（每次登陆的时候进行一次更新，防止变化）
	   * @return
	   */
	  public static String login(){
		return pre+"user/login"; 
	  }
	  
	  /**
	   * 修改用户心情颜色
	   * 提供的参数：uid，happy（高兴时的颜色），angry（生气时候的颜色），sad（伤心时候的颜色），scard（害怕时候的颜色）
	   * commen（一般时候的颜色）
	   * @return
	   */
	  public static String updateUserMoodColoor(){
		return pre+"user/updateUserMoodColoor";
		  
	  }
	  
	  /**
	   * 查看用户心情颜色
	   * 提供的参数：uid
	   * @return
	   */
	  public static String selectUserMoodColoor(){
		return pre+"user/selectUserMoodColoor";
		  
	  }
	  //=======================================================================
	  /**
	   * 查看所有分类
	   * 无参数
	   * @return
	   */
	  public static String selectAllCategory(){
		return pre+"category/selectAllCategory";
		  
	  }
	  //=================================好友模块=====================================
	  
	 /**
	  * 添加好友之前的模糊查询所有符合条件的用户
	  * @return
	  */
	  public static String vagueSelectFriend(){
		return pre+ "friend/vagueSelectFriend";
	  }
	  /**
	   * 添加好友
	   * 提供的参数：number（好友的账号），uid（用户的uid），friendUsername（给好友的备注）
	   * @return
	   */
	  public static String addFriend(){
		return pre+"friend/addFriend";
		  
	  }
	  /**
	   * 修改好友备注
	   * 提供的参数：fid（好友的fid），friendUsername（好友的备注）
	   * @return
	   */
	  public static String updateFriendName(){
		return pre+"friend/updateFriendName";
		  
	  }
	  
	  /**
	   * 删除好友
	   * 提供的参数：fid
	   * @return
	   */
	  public static String deleteFriend(){
		return pre+"friend/deleteFriend";
		  
	  }
	  /**
	   * 查询全部好友
	   * 提供的参数：uid
	   * @return
	   */
	  public static String selectAllFriends(){
		return pre+"friend/selectAllFriends";
		  
	  }
	  /**
	   * 查询某一好友模糊查询
	   * 提供的参数：uid，selectName（查询的条件）
	   * @return
	   */
	  public static String selectFriend(){
		return pre+"friend/selectFriend";
	  }
	  
	  /**
	   * 查询删除的好友
	   * 提供的参数：uid
	   * @return
	   */
	  public static String selectDeleteFriend(){
		return pre+"friend/selectDeleteFriend";
		  
	  }
	  //==============================好友便签模块======================================
	  /**
	   * 添加好友便签
	   * 提供的参数：uid，fid，memoName（便签标题），friendContent（便签内容）
	   * @return
	   */
	  public static String addMemo(){
		return pre+"friend/addMemo";
		  
	  }
	  
	  /**
	   * 修改好友便签
	   * 提供的参数：memoId（json串），memoName（json串），friendContent（json串）
	   * @return
	   */
	  public static String updateMemo(){
		return pre+"friend/updateMemo";
	  }
	  
	  /**
	   * 删除便签
	   * 提供的参数：memoId
	   * @return
	   */
	  public static String deleteMemo(){
		return pre+"friend/deleteMemo";
		  
	  }
	  /**
	   * 查看好友详细信息
	   * 提供的参数：uid（用户的uid），friendNumber（好友的账号），fid（好友的fid）
	   * @return
	   */
	  public static String selectFriendData(){
		return pre+"friend/selectFriendData";
		  
	  }
	  //===========================漂流瓶模块==============================

	  /**
	   * 添加漂流瓶
	   * 所需的参数：uid（用户的uid），title（漂流瓶标题），driftContent（漂流记录的内容），identifier（样式编号：数字）
	   * @return
	   */
	  public static String addDrift_note(){
			return pre+"driftBottle/addDrift_note";
			  
		  }
	  /**
	   * 添加漂流瓶评论
	   * 所需的参数：driftId（漂流瓶的id），drifCommentId（评论人的uid），drifIfObv（是否匿名 数字），drifContent（评论内容）
	   * @return
	   */
	  public static String addDrift_evaluate_discuss(){
			return pre+"driftBottle/addDrift_evaluate_discuss";
			  
		  }
	  /**
	   * 仍回大海
	   * 所需的参数：driftId（漂流瓶的id）
	   * @return
	   */
	  public static String atSea(){
			return pre+"driftBottle/atSea";
			  
		  }
	  /**
	   * 厌恶此漂流瓶
	   * 所需参数：driftId
	   * 注意：一旦点击了厌恶，此漂流瓶将永久不被拾起
	   * @return
	   */
	  public static String hate(){
			return pre+"driftBottle/hate";
			  
		  }
	  
	  /**
	   * 随机抽取一条漂流瓶和其所有评论且不是此用户的
	   * 所需参数：uid，
	   * @return
	   */
	  public static String randomSelectDrift_note(){
			return pre+"driftBottle/randomSelectDrift_note";
			  
		  }
	  //====================纸条模块============================
	  /**
	   * 发布纸条
	   * 所需参数：uid（发布纸条人的uid），mood（纸条设置的心情颜色id），noteAdout（纸条关于的人的userNumber），noteContent（纸条的内容）
	   * 		   friendNumberList（设置权限勾选的亲友的friendNumber），obvious（设置权限的方式0：设置可见用户，其他用户不可见；1：设置不可见用户，其他用户可见）
	   */
	  public static String addNote(){
		  return pre+"note/addNote";
	  }
	  /**
	   * 点赞
	   * 所需参数：noteId（点赞纸条id）
	   * @return
	   */
	  public static String getGoodNum(){
		  return pre+"note/getGoodNum";
	  }
	  /**
	   * 扔鸡蛋
	   * 所需参数：noteId（扔鸡蛋纸条id）
	   * @return
	   */
	  public static String getEgg(){
		  return pre+"note/getEgg";
	  }
	  /**
	   * 分页显示用户的所有字条
	   * 所需参数：noteId（此为分页的最后一条字条的id，第一次为0），uid（要显示字条用户的uid），
	   * @return
	   */
	  public static String showMyAllNote(){
		  return pre+"note/showMyAllNote";
	  }
	  /**
	   * 显示某个亲友的自己有权限看的字条
	   * 所需参数：myuserNunber（当前用户的userNumber），friUid（要看的亲友的uid），noteId（此为分页的最后一条字条的id，第一次为0）
	   * @return
	   */
	  public static String showOneFriNote(){
		  return pre+"note/showOneFriNote";
	  }
	  /**
	   * 显示所有亲友的自己有权限看的字条
	   * 所需参数：uid（当前用户uid），myuserNunber（当前用户userNumber），noteId（此为分页的最后一条字条的id，第一次为0）
	   * @return
	   */
	  public static String showAllfriNote(){
		  return pre+"note/showAllfriNote";
	  }
	  
	  //==============评论模块=====================
	  /**
	   * 发表评论
	   * 所需参数：noteId（评回的纸条的id），commentId（评论人id），ifObv（是否匿名0是,1否），econtent（评论内容）
	   * @return
	   */
	  public static String pushcomment(){
		  return pre+"evaluate/pushcomment";
	  }
	  /**
	   * 发表回复
	   * 所需参数：noteId（评回的纸条id），commentId（被回复的人的id），replyId（回复的人的id），ifObv（是否匿名0是,1否），econtent（回复内容）
	   *           eflag（被回复的评回的标志位）/标志位 1：为评价纸条 2：为回复评价的纸条 3：为回复回复的纸条
	   * @return
	   */
	  public static String pushReply(){
		  return pre+"evaluate/pushReply";
	  }
}

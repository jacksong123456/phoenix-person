package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;

/**
 * 文件存放记录表
 * @author liu
 * 创建时间：2012-2-2 10:38:41
 */
/**
 * @author liu
 * 
 */
public class FileIndex implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 6755175107258258053L;
	private String id;// 主键
	private String filePath;// 文件存放的物理路径
	private String fileTitle;// 源文件的名称
	private Long fileSize; // 文件大小
	private Boolean status; //文件状态
	@Transient
	private Long author; //创建人
	private String md5; // 加密形式
	private String taskId; //taskId
	private String ctime; //创建时间
	private Integer moduleType; //类型
	private String authorName; //创建人姓名
	private String crc32;      //解压缩
	public String getId() {
		if (id==null){
			id="" ;
		}
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilePath() {
		if (filePath==null){
			filePath="" ;
		}
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileTitle() {
		if (fileTitle==null){
			fileTitle="" ;
		}
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public long getFileSize() {
		if (fileSize==null){
			fileSize=0l ;
		}
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	

	public long getAuthor() {
		if (author==null){
			author=0l ;
		}
		return author;
	}

	public void setAuthor(long author) {
		this.author = author;
	}

	public String getMd5() {
		if (md5==null){
			md5="" ;
		}

		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getTaskId() {
		if (taskId==null){
			taskId="" ;
		}
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getCtime() {
		if (ctime==null){
			ctime="" ;
		}
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public int getModuleType() {
		if (moduleType==null){
			moduleType =0 ;
		}
		return moduleType;
	}

	public String getAuthorName() {
        if (authorName==null){
			authorName="" ;
		}
		return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setModuleType(int moduleType) {
		this.moduleType = moduleType;
	}

	public String getCrc32() {
		if (crc32==null){
			crc32="" ;
		}
		return crc32;
	}

	public void setCrc32(String crc32) {
		this.crc32 = crc32;
	}

}

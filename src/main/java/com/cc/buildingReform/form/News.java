package com.cc.buildingReform.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "BR_NEWS")
public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NEWS_ID", unique = true, nullable = false)
	private Integer id;

	@Column(name = "NEWS_MENU_ID")
	private Integer menuId;

	@Column(name = "NEWS_TITLE")
	private String title;

	@Column(name = "NEWS_SUB_TITLE")
	private String subTitle;

	@Column(name = "NEWS_CONTENT")
	private String content;

	@Column(name = "NEWS_AUTHOR")
	private String author;

	@Column(name = "NEWS_SOURCE")
	private String source;

	@Column(name = "NEWS_BIG_IMAGE")
	private String bigImage;

	@Column(name = "NEWS_MIDDLE_IMAGE")
	private String middleImage;

	@Column(name = "NEWS_SMALL_IMAGE")
	private String smallImage;

	@Column(name = "NEWS_VIDEO")
	private String video;

	@Column(name = "NEWS_LINK")
	private String link;

	@Column(name = "NEWS_FILE")
	private String file;

	@Column(name = "NEWS_ORDER")
	private Integer order;

	@Column(name = "NEWS_ISTOP")
	private Integer istop;

	@Column(name = "NEWS_ISNEW")
	private Integer isnew;

	@Column(name = "NEWS_AUDIT")
	private Integer audit;

	@Column(name = "NEWS_HITS")
	private Integer hits;

	@Column(name = "NEWS_KEY")
	private String key;

	@Column(name = "NEWS_DES")
	private String des;

	@Column(name = "NEWS_FLAG")
	private Integer flag;

	@Column(name = "NEWS_DATE")
	private Date date;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NEWS_USER_ID")
	@Cascade({ CascadeType.REFRESH })
	private User user;
	
	@Column(name = "NEWS_REMARK")
	private String remark;

	@Column(name = "NEWS_IP")
	private Integer ip;

	@Column(name = "NEWS_ORIGIN_ID")
	private Integer originId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getTitle() {
		if (title == null) {
			return "";
		}
		else {
			return title;
		}
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		if (subTitle == null) {
			return "";
		}
		else {
			return subTitle;
		}
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getContent() {
		if (content == null) {
			return "";
		}
		else {
			return content;
		}
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		if (author == null) {
			return "";
		}
		else {
			return author;
		}
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getBigImage() {
		return bigImage;
	}

	public void setBigImage(String bigImage) {
		this.bigImage = bigImage;
	}

	public String getMiddleImage() {
		return middleImage;
	}

	public void setMiddleImage(String middleImage) {
		this.middleImage = middleImage;
	}

	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getIstop() {
		return istop;
	}

	public void setIstop(Integer istop) {
		this.istop = istop;
	}

	public Integer getIsnew() {
		return isnew;
	}

	public void setIsnew(Integer isnew) {
		this.isnew = isnew;
	}

	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIp() {
		if (ip == null) {
			return 0;
		}
		else {
			return ip;
		}
	}

	public void setIp(Integer ip) {
		this.ip = ip;
	}

	public Integer getOriginId() {
		if (originId == null) {
			return 0;
		}
		else {
			return originId;
		}
	}

	public void setOriginId(Integer originId) {
		this.originId = originId;
	}

}

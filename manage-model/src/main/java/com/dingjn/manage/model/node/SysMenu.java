package com.dingjn.manage.model.node;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * @author dingjn
 * @since 2020-06-13
 */
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父菜单ID
     */
    private Integer menuPid;

    /**
     * 当前菜单所有父菜单
     */
    private String menuPids;

    /**
     * 0:不是叶子节点，1:是叶子节点
     */
    private Boolean isLeaf;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 跳转URL
     */
    private String url;

    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单层级
     */
    private Integer level;

    /**
     * 是否禁用，0:启用(否）,1:禁用(是)
     */
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(Integer menuPid) {
        this.menuPid = menuPid;
    }

    public String getMenuPids() {
        return menuPids;
    }

    public void setMenuPids(String menuPids) {
        this.menuPids = menuPids;
    }

    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
        ", id=" + id +
        ", menuPid=" + menuPid +
        ", menuPids=" + menuPids +
        ", isLeaf=" + isLeaf +
        ", menuName=" + menuName +
        ", url=" + url +
        ", icon=" + icon +
        ", sort=" + sort +
        ", level=" + level +
        ", status=" + status +
        "}";
    }
}

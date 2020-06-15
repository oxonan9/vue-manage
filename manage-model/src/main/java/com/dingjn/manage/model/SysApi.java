package com.dingjn.manage.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 系统Http接口表，配合sys_role_api控制接口访问权限
 * </p>
 *
 * @author dingjn
 * @since 2020-06-13
 */
public class SysApi extends Model<SysApi> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 接口父ID(即接口分组)
     */
    private Integer apiPid;

    /**
     * 当前接口的所有上级id(即所有上级分组)
     */
    private String apiPids;

    /**
     * 0:不是叶子节点，1:是叶子节点
     */
    private Boolean isLeaf;

    /**
     * 接口名称
     */
    private String apiName;

    /**
     * 跳转URL
     */
    private String url;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 层级，1：接口分组，2：接口
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

    public Integer getApiPid() {
        return apiPid;
    }

    public void setApiPid(Integer apiPid) {
        this.apiPid = apiPid;
    }

    public String getApiPids() {
        return apiPids;
    }

    public void setApiPids(String apiPids) {
        this.apiPids = apiPids;
    }

    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        return "SysApi{" +
        ", id=" + id +
        ", apiPid=" + apiPid +
        ", apiPids=" + apiPids +
        ", isLeaf=" + isLeaf +
        ", apiName=" + apiName +
        ", url=" + url +
        ", sort=" + sort +
        ", level=" + level +
        ", status=" + status +
        "}";
    }
}

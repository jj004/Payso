package com.merchantcollection.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "MST_ROLE")
public class Role extends Traceability {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long roleId;
	
	private String roleName;
	@Column(columnDefinition="VARCHAR(1)")
	private char roleStatus;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "MST_ROLE_MENU", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"), inverseJoinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "menuId"))
	private Set<Menu> menus;
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL,orphanRemoval=true)
    private List<User> userList = new ArrayList<User>();

	
	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public char getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(char roleStatus) {
		this.roleStatus = roleStatus;
	}

	@Override
    public String toString() {
        String result = String.format(
                "Role [id=%d, name='%s']%n",
                roleId, roleName);
        if (menus != null) {
            for(Menu menu : menus) {
                result += String.format(
                        "Menu[id=%d, name='%s']%n",
                        menu.getMenuId(), menu.getMenuName());
            }
        }

        return result;
    }
}

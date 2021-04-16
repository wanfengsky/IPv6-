package com.yi.project.common;

import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 內存緩存用以转换ID-名称
 */
public abstract class ModelsMap<T> {
	protected Map<Long, T> MODELS_MAP;

	@PostConstruct
	protected void init() {
		MODELS_MAP = new HashMap<>();
		List<T> models = selectList();
		if (CollectionUtils.isNotEmpty(models)) {
			models.forEach(cg -> MODELS_MAP.put(getId(cg), cg));
		}
	}

	/** 获取所有数据的方法 */
	protected abstract List<T> selectList();

	/** 获取单条数据的方法 */
	protected abstract T selectById(Long id);

	/** 从数据中读取ID */
	protected abstract Long getId(T model);

	/** 从数据中读取Name */
	protected abstract String getName(T model);

	/**
	 * 通过ID获取组名字
	 *
	 * @param id
	 * @return
	 */
	public String getName(Long id) {
		T model = get(id);
		if (model != null) {
			return getName(model);
		}
		return null;
	}

	/**
	 * 通过ID获取组信息
	 *
	 * @param id
	 * @return
	 */
	public T get(Long id) {
		if (id == null) return null;
		if (MODELS_MAP.containsKey(id)) {
			return MODELS_MAP.get(id);
		} else {
			boolean result = refresh(id);
			if (result) {
				return MODELS_MAP.get(id);
			}
			return null;
		}
	}

	/**
	 * 刷新组信息
	 *
	 * @param model
	 * @return
	 */
	public boolean refresh(T model) {
		if (model != null) {
			MODELS_MAP.put(getId(model), model);
			return true;
		}
		return false;
	}

	/**
	 * 通过ID刷新组信息
	 *
	 * @param id
	 * @return
	 */
	public boolean refresh(Long id) {
		T model = selectById(id);
		if (model != null) {
			MODELS_MAP.put(getId(model), model);
			return true;
		}
		return false;
	}

	/**
	 * 通过ID清除组信息
	 *
	 * @param ids
	 * @return
	 */
	public void flush(Long... ids) {
		for (Long id : ids) {
			MODELS_MAP.remove(id);
		}
	}
}

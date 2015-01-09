package com.base.sys.sys_resource.service;

import com.base.sys.sys_resource.model.SysResource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zkdu on 2015/1/9.
 */
@Service
public interface SysResourceService {

    List<SysResource> getAll();
}

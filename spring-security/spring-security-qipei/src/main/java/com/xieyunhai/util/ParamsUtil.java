package com.xieyunhai.util;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @since 2017/9/22 14:58
 */
public class ParamsUtil {

	public static Map<String, Object> getParams(String[] keys, Object[] values) {
		// todo 改为 stream 操作
		Map<String, Object> result = new HashMap<>();
		for (int i = 0; i < keys.length; i++) {
			result.put(keys[i], values[i]);
		}
		return result;
	}

	// todo  改成 lambda 表达式
	public static String[] getFieldsName(String className, String methodName) throws Exception {
		Class<?> clazz = Class.forName(className);
		String clazzName = clazz.getName();
		ClassPool pool = ClassPool.getDefault();
		ClassClassPath classPath = new ClassClassPath(clazz);
		pool.insertClassPath(classPath);

		CtClass ctClass = pool.get(clazzName);
		CtMethod ctMethod = ctClass.getDeclaredMethod(methodName);
		MethodInfo methodInfo = ctMethod.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		if (attr == null) {
			return null;
		}
		String[] paramsArgsName = new String[ctMethod.getParameterTypes().length];
		int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
		for (int i = 0; i < paramsArgsName.length; i++) {
			paramsArgsName[i] = attr.variableName(i + pos);
		}
		return paramsArgsName;
	}
}

package serverPackage;

public enum RequestType {
	Add, Read, Write, Delete;

	public static RequestType getType(String type) {
		
		
		switch(type){
			case "Add": return Add;
			case "Read": return Read;
			case "Write": return Write;
			case "Delete": return Delete;
		}
		return null;
		
		/*
		for(RequestType rt : RequestType.values())
			if(rt.toString() == type)
				return rt;
		return null;*/
	}
}

package gawe.imb.karya.model.objects.gaweResponse;

public class UpdateRoleResponse{
	private String process;
	private int statusCode;
	private String status;

	public void setProcess(String process){
		this.process = process;
	}

	public String getProcess(){
		return process;
	}

	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
	}

	public int getStatusCode(){
		return statusCode;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"UpdateRoleResponse{" + 
			"process = '" + process + '\'' + 
			",status_code = '" + statusCode + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}

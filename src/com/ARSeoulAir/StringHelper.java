package com.ARSeoulAir;

public class StringHelper {
	private String m_strResult = "";
	private String m_strBase = "";
	
	public StringHelper(String strBase)
	{
		m_strBase = strBase;
	}
	
	public String getResult()
	{
		return m_strResult;
	}
	
	public String getBase()
	{
		return m_strBase;
	}
	
	public Boolean find(String strQueryStart, String strQueryEnd, Boolean bRemoveQuery)
    {
		if(m_strBase.length() <= 0)
			return false;
	
        if (findStart(strQueryStart, bRemoveQuery) < 0)
        {
            if (findStart(strQueryEnd, bRemoveQuery) >= 0)
            {
                return true;
            }
            return false;
        }
        
        m_strResult = findEnd(strQueryEnd);
        return true;
    }
	
	public int findStart(String strQuery, Boolean bRemoveQuery)
    {
        int nIndex = 0;
        nIndex = m_strBase.indexOf(strQuery);
        if (nIndex != -1)
        	m_strBase = m_strBase.substring(nIndex);
        
        if (bRemoveQuery)
        {
            int nQueryCnt = strQuery.length();
            if (nQueryCnt <= m_strBase.length())
            {
            	m_strBase = m_strBase.substring(nQueryCnt);
            }

            //nReval -= nQueryCnt;
        }
        return nIndex;
    }

    public String findEnd(String strQuery)
    {
    	String strReval = "";
        int nTempIndex;
        nTempIndex = m_strBase.indexOf(strQuery);
        strReval = m_strBase.substring(0, nTempIndex);
        m_strBase = m_strBase.substring(nTempIndex);
        return strReval;
    }
}

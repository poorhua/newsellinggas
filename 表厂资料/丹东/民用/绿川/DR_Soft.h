extern "C"
{
int _stdcall WriteGjkCard(__int16 com,__int32 baut,unsigned char *kmm,int n,unsigned char *gasMetertype, __int32 bjql,__int32 czsx ,__int32 tzed ,__int32 Csql);
int _stdcall ReadInitCard(__int16 com,__int32 baut,unsigned char *gasMetertype,  __int32 *Bjql, __int32 *czsx,  __int32 *tzql, __int32 *testgasvalue);
int __stdcall ReadGasCard(__int16 com, __int32 baut, unsigned char *kmm, __int16 *klx, __int16 *kzt, unsigned char *kh, unsigned char *dqdm, unsigned char *yhh, unsigned char *tm, __int32 *ql, __int16 *cs, __int32 *ljgql,__int16 *bkcs, __int32 *ljyql, __int32 *syql, __int32 *bjql, __int32 *czsx, __int32 *tzed,unsigned char *sqrq ,__int32  *oldprice, __int32 *newprice ,unsigned char *sxrq,unsigned char *sxbj)
	;
int __stdcall WriteNewCard(__int16 com, __int32 baut, unsigned char *kmm, __int16 klx, __int16 kzt, unsigned char *kh, unsigned char *dqdm, unsigned char *yhh, unsigned char *tm, __int32 ql, __int16 cs, __int32 ljgql, __int16 bkcs, __int32 ljyql, __int32 bjql, __int32 czsx, __int32 tzed,unsigned char *sqrq ,__int32  oldprice, __int32  newprice ,unsigned char * sxrq,unsigned char * sxbj);
int _stdcall CheckGasCard(__int16 com,__int32 baut);
int __stdcall FormatGasCard (__int16 com, __int32 baut, unsigned char *kmm, __int16 klx, unsigned char *kh, unsigned char *dqdm);

int _stdcall WriteGasCard(__int16 com, __int32 baut, unsigned char *kmm, __int16 klx, unsigned char *kh, unsigned char *dqdm,unsigned char *yhh, __int32 ql, __int16 cs, __int32 ljgql, __int32 bjql, __int32 czsx, __int32 tzed,unsigned char *sqrq ,__int32  oldprice, __int32  newprice ,unsigned char * sxrq,unsigned char * sxbj);	
}
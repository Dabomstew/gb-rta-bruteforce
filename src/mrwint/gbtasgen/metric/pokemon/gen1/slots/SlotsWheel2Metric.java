package mrwint.gbtasgen.metric.pokemon.gen1.slots;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.EflUtil;

public class SlotsWheel2Metric implements StateResettingMetric {

	@Override
	public int getMetricInternal() {
//    System.out.println("SW2");
	  do {
      EflUtil.runToAddressNoLimit(0, 0, 0x374cf); // after SlotMachine_374fb
      EflUtil.runToAddressNoLimit(0, 0, 0x374cc); // before SlotMachine_374fb
      if (curGb.readMemory(0xcd3d) != 2) // wrong wheel
        return 0;
      System.out.println(curGb.readMemory(0xcd4e));
	  } while (curGb.readMemory(0xcd4e) != 0);
    if (curGb.readMemory(0xcd45) == 2)
      return 1;
    if (curGb.readMemory(0xcd44) == 2 && curGb.readMemory(0xcd43) != 2)
      return 1;
    if (curGb.readMemory(0xcd46) == 2 && curGb.readMemory(0xcd41) != 2)
      return 1;
	  return 0;
	}
}

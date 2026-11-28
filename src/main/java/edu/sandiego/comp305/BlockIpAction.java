package edu.sandiego.comp305;

public class BlockIpAction implements ResponseAction {

    private final BlockList blockList;

    private final String ip;

    public BlockIpAction(final BlockList blockList, final String ip) {
        this.blockList = blockList;
        this.ip = ip;
    }

    @Override
    public void undo() {
        blockList.unblock(ip);
    }

    @Override
    public String describe() {
        return "Block IP " + ip;
    }

    @Override
    public void execute(final Incident incident) {
        blockList.block(ip);
    }
}
